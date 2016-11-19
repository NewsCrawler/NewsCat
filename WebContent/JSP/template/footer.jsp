<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js" integrity="sha256-zXNrZH6Aqd2T5QyZumro0VuxbhdKhOiQhxtw6YxgjUM=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/9.4.1/bootstrap-slider.min.js" integrity="sha256-bxBd/RJ3YxYEmFmkD2h2fGFlI4dHjYW5rjGXggr5XSM=" crossorigin="anonymous"></script>
    <script>
	    $('.input-daterange').datepicker({
	    	format: "yyyy-mm-dd",
			startDate: "2016-10-25",
			endDate: "2016-11-19"
	    });
	    
	    $("#ex8").slider({
	    	tooltip: 'always'
	    });
	    $("#ex8").on("slideStop", function(slideEvt){
	    	console.log(slideEvt.value);
	    	$.get( "/Graph?dDay=" + slideEvt.value, function( data ) {
	    		  
	    		  console.log(data);
	    		  
	    		  $("svg g *").remove();
	    		  
	    		  d3.json("/Graph?dDay=" + slideEvt.value, function(error, graph) {
	    			    var linkedByIndex = {};
	    			    graph.links.forEach(function(d) {
	    			        linkedByIndex[d.source + "," + d.target] = true;
	    			    });

	    			    function isConnected(a, b) {
	    			        return linkedByIndex[a.index + "," + b.index] || linkedByIndex[b.index + "," + a.index] || a.index == b.index;
	    			    }

	    			    function hasConnections(a) {
	    			        for (var property in linkedByIndex) {
	    			            s = property.split(",");
	    			            if ((s[0] == a.index || s[1] == a.index) && linkedByIndex[property]) return true;
	    			        }
	    			        return false;
	    			    }
	    			    
	    			    force
	    			        .nodes(graph.nodes)
	    			        .links(graph.links)
	    			        .start();

	    			    var link = g.selectAll(".link")
	    			        .data(graph.links)
	    			        .enter().append("line")
	    			        .attr("class", "link")
	    			        .style("stroke-width",nominal_stroke)
	    			        .style("stroke", function(d) { 
	    			            if (isNumber(d.score) && d.score>=0) return color(d.score);
	    			            else return default_link_color;
	    			        })

	    			    var node = g.selectAll(".node")
	    			        .data(graph.nodes)
	    			        .enter().append("g")
	    			        .attr("class", "node")
	    			        .call(force.drag)

	    			    node.on("dblclick",function(d){
	    			        console.log(d.id);
	    			    });
	    			        
	    			    node.on("dblclick.zoom", function(d) {
	    			        d3.event.stopPropagation();
	    			        var dcx = (ww/2-d.x*zoom.scale());
	    			        var dcy = (hh/2-d.y*zoom.scale());
	    			        zoom.translate([dcx,dcy]);
	    			        g.attr("transform", "translate("+ dcx + "," + dcy  + ")scale(" + zoom.scale() + ")");
	    			    });
	    			      
	    			    var tocolor = "fill";
	    			    var towhite = "stroke";
	    			    if (outline) {
	    			        tocolor = "stroke"
	    			        towhite = "fill"
	    			    }

	    			    var circle = node.append("path")  
	    			        .attr("d", d3.svg.symbol()
	    			        .size(function(d) { return Math.PI*Math.pow(size(d.size)||nominal_base_node_size,2); })
	    			        .type(function(d) { return d.type; }))
	    			        .style(tocolor, function(d) { 
	    			            if (isNumber(d.score) && d.score>=0) return color(d.score);
	    			            else return default_node_color; })
	    			        //.attr("r", function(d) { return size(d.size)||nominal_base_node_size; })
	    			        .style("stroke-width", nominal_stroke)
	    			        .style(towhite, "white");

	    			     var text = g.selectAll(".text")
	    			        .data(graph.nodes)
	    			        .enter().append("text")
	    			        .attr("dy", ".35em")
	    			        .style("font-size", nominal_text_size + "px")

	    			    if (text_center){
	    			        text.text(function(d) { return d.id; })
	    			            .style("text-anchor", "middle");
	    			    }
	    			    else {
	    			        text.attr("dx", function(d) {return (size(d.size)||nominal_base_node_size);})
	    			            .text(function(d) {
	    			                return d.id;
	    			                //return '\u2002'+d.id;
	    			            });
	    			    }

	    			    node.on("mouseover", function(d) {
	    			            set_highlight(d);
	    			        })
	    			        .on("mousedown", function(d) {
	    			            d3.event.stopPropagation();
	    			            focus_node = d;
	    			            set_focus(d)
	    			            if (highlight_node === null) set_highlight(d)
	    			        })
	    			        .on("mouseout", function(d) {
	    			            exit_highlight();
	    			        });
	    			        
	    			    d3.select(window).on("mouseup", function() {
	    			        if (focus_node!==null) {
	    			            focus_node = null;
	    			            if (highlight_trans<1) {
	    			                circle.style("opacity", 1);
	    			                text.style("opacity", 1);
	    			                link.style("opacity", 1);
	    			            }
	    			        }
	    			        if (highlight_node === null) exit_highlight();
	    			    });

	    			    function exit_highlight() {
	    			            highlight_node = null;
	    			        if (focus_node===null) {
	    			            svg.style("cursor","move");
	    			            if (highlight_color!="white") {
	    			                circle.style(towhite, "white");
	    			                text.style("font-weight", "normal");
	    			                link.style("stroke", function(o) {return (isNumber(o.score) && o.score>=0)?color(o.score):default_link_color});
	    			            }
	    			        }
	    			    }

	    			    function set_focus(d) { 
	    			        if (highlight_trans<1) {
	    			            circle.style("opacity", function(o) {
	    			                return isConnected(d, o) ? 1 : highlight_trans;
	    			            });
	    			            text.style("opacity", function(o) {
	    			                return isConnected(d, o) ? 1 : highlight_trans;
	    			            });
	    			            link.style("opacity", function(o) {
	    			                return o.source.index == d.index || o.target.index == d.index ? 1 : highlight_trans;
	    			            });     
	    			        }
	    			    }

	    			    function set_highlight(d) {
	    			        svg.style("cursor","pointer");
	    			        if (focus_node!==null) d = focus_node;
	    			        highlight_node = d;
	    			        if (highlight_color!="white") {
	    			            circle.style(towhite, function(o) {
	    			                return isConnected(d, o) ? highlight_color : "white";
	    			            });
	    			            text.style("font-weight", function(o) {
	    			                return isConnected(d, o) ? "bold" : "normal";
	    			            });
	    			            link.style("stroke", function(o) {
	    			                return o.source.index == d.index || o.target.index == d.index ? highlight_color : ((isNumber(o.score) && o.score>=0)?color(o.score):default_link_color);
	    			            });
	    			        }
	    			    }

	    			    zoom.on("zoom", function() {
	    			        var stroke = nominal_stroke;
	    			        if (nominal_stroke*zoom.scale()>max_stroke) stroke = max_stroke/zoom.scale();
	    			        link.style("stroke-width",stroke);
	    			        circle.style("stroke-width",stroke);
	    			           
	    			        var base_radius = nominal_base_node_size;
	    			        if (nominal_base_node_size*zoom.scale()>max_base_node_size) base_radius = max_base_node_size/zoom.scale();
	    			            circle.attr("d", d3.svg.symbol()
	    			            .size(function(d) { return Math.PI*Math.pow(size(d.size)*base_radius/nominal_base_node_size||base_radius,2); })
	    			            .type(function(d) { return d.type; }))
	    			            
	    			        //circle.attr("r", function(d) { return (size(d.size)*base_radius/nominal_base_node_size||base_radius); })
	    			        if (!text_center) text.attr("dx", function(d) { return (size(d.size)*base_radius/nominal_base_node_size||base_radius); });

	    			        var text_size = nominal_text_size;
	    			        if (nominal_text_size*zoom.scale()>max_text_size) text_size = max_text_size/zoom.scale();
	    			        text.style("font-size",text_size + "px");

	    			        g.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
	    			    });
	    			    
	    			    svg.call(zoom);     
	    			        
	    			    resize();
	    			    //window.focus();
	    			    d3.select(window).on("resize", resize).on("keydown", keydown);
	    			      
	    			    force.on("tick", function() {

	    			    node.attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });
	    			    text.attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });

	    			    link.attr("x1", function(d) { return d.source.x; })
	    			      .attr("y1", function(d) { return d.source.y; })
	    			      .attr("x2", function(d) { return d.target.x; })
	    			      .attr("y2", function(d) { return d.target.y; });
	    			        
	    			    node.attr("cx", function(d) { return d.x; })
	    			      .attr("cy", function(d) { return d.y; });
	    			    });

	    			    function resize() {
	    			        var width = ww, height = hh;
	    			        svg.attr("width", width).attr("height", height);

	    			        force.size([force.size()[0]+(width-w)/zoom.scale(),force.size()[1]+(height-h)/zoom.scale()]).resume();
	    			        w = width;
	    			        h = height;
	    			    }

	    			    function keydown() {
	    			        if (d3.event.keyCode==32) {
	    			                force.stop();
	    			        }
	    			        else if (d3.event.keyCode>=48 && d3.event.keyCode<=90 && !d3.event.ctrlKey && !d3.event.altKey && !d3.event.metaKey) {
	    			            switch (String.fromCharCode(d3.event.keyCode)) {
	    			                case "C": keyc = !keyc; break;
	    			                case "S": keys = !keys; break;
	    			                case "T": keyt = !keyt; break;
	    			                case "R": keyr = !keyr; break;
	    			                case "X": keyx = !keyx; break;
	    			                case "D": keyd = !keyd; break;
	    			                case "L": keyl = !keyl; break;
	    			                case "M": keym = !keym; break;
	    			                case "H": keyh = !keyh; break;
	    			                case "1": key1 = !key1; break;
	    			                case "2": key2 = !key2; break;
	    			                case "3": key3 = !key3; break;
	    			                case "0": key0 = !key0; break;
	    			            }

	    			            link.style("display", function(d) {
	    			                var flag  = vis_by_type(d.source.type)&&vis_by_type(d.target.type)&&vis_by_node_score(d.source.score)&&vis_by_node_score(d.target.score)&&vis_by_link_score(d.score);
	    			                linkedByIndex[d.source.index + "," + d.target.index] = flag;
	    			                return flag?"inline":"none";
	    			            });
	    			            
	    			            node.style("display", function(d) {
	    			                return (key0||hasConnections(d))&&vis_by_type(d.type)&&vis_by_node_score(d.score)?"inline":"none";
	    			            });
	    			            
	    			            text.style("display", function(d) {
	    			                return (key0||hasConnections(d))&&vis_by_type(d.type)&&vis_by_node_score(d.score)?"inline":"none";
	    			            });
	    			                
	    			            if (highlight_node !== null)
	    			            {
	    			                if ((key0||hasConnections(highlight_node))&&vis_by_type(highlight_node.type)&&vis_by_node_score(highlight_node.score)) { 
	    			                if (focus_node!==null) set_focus(focus_node);
	    			                set_highlight(highlight_node);
	    			                }
	    			                else {exit_highlight();}
	    			            }
	    			        }   
	    			    } 
	    			});
    		});
	    });
    </script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-55359566-5', 'auto');
  ga('send', 'pageview');

	</script>
  </body>
</html>