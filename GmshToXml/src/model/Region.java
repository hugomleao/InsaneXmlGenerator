package model;

public class Region {

	private String label;
	private int tag;
	
	public Region (String label, int number) {
		this.tag = number;
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	
	
}
