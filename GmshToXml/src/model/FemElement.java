package model;

import java.util.ArrayList;

public class FemElement {
	
	private ArrayList <Node> nodes = new ArrayList<Node>();
	private Region region;
	private int id;
	
	public FemElement() {
		
	}
	
	public FemElement (int id, Region region, ArrayList<Node> nodes) {
		this.id = id;
		this.region = region;
		this.nodes = nodes;
	}
	
	public String getIncidence() {
		String resp = "";
		for (int i = 0; i < nodes.size(); i++) {
			if (this.positiveDirection()) {
			resp += nodes.get(i).getId() + " ";
			} else {
				resp += nodes.get(nodes.size() - 1 - i).getId() + " ";
			}
		}
		return resp.trim();
	}
	
	private boolean positiveDirection() {
		Node n1 = this.nodes.get(0);
		Node n2 = this.nodes.get(1);
		Node n3 = this.nodes.get(2);
		double x1 = n2.getX() - n1.getX();
		double y1 = n2.getY() - n1.getY();
		double x2 = n3.getX() - n2.getX();
		double y2 = n3.getY() - n2.getY();
		double vectorial = x1 * y2 - x2 * y1;
		return vectorial > 0;
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	
	
}
