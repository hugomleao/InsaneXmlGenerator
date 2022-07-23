package model.gmsh;

public class PhysicalName {
	
	private int dimension;
	private int physicalTag;
	private String name;
	
	public PhysicalName() {
		
	}
	
	public PhysicalName(int dimension, int physicalTag, String name) {
		this.dimension = dimension;
		this.physicalTag = physicalTag;
		this.name = name;
	}
	
	
	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public int getPhysicalTag() {
		return physicalTag;
	}
	public void setPhysicalTag(int physicalTag) {
		this.physicalTag = physicalTag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "PhysicalTag: " + this.physicalTag;
	}

	
}
