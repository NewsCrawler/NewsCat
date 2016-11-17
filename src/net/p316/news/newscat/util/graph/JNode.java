package net.p316.news.newscat.util.graph;

public class JNode {
	public int size = 20;
	public double score = 0.0;
	public String id = "노드";
	public String type = "circle";
	
	public JNode(){
		
	}
	
	public JNode(int _size, double _scroe, String _id){
		size = _size;
		score = _scroe;
		id = _id;
	}
	
	public JNode(int _size, double _scroe, String _id, String _type){
		size = _size;
		score = _scroe;
		id = _id;
		type = _type;
	}
}