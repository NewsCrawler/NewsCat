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