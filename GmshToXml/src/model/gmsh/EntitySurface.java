package model.gmsh;

public class EntitySurface {
	private int tag;
	private int physicalTag;
	
	public EntitySurface() {
		
	}
	
	public EntitySurface(int tag, int physicalTag) {
		this.tag = tag;
		this.physicalTag = physicalTag;
	}
	
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public int getPhysicalTag() {
		return physicalTag;
	}
	public void setPhysicalTag(int physicalTag) {
		this.physicalTag = physicalTag;
	}
}
