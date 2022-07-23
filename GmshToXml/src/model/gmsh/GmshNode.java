package model.gmsh;

public class GmshNode {
	
	int tag;
	double x;
	double y;
	double z;
	Entity entity;
	
	public GmshNode() {
		
	}
	
	public GmshNode(Entity entity, int tag, double x, double y, double z) {
		this.entity = entity;
		this.tag = tag;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	
	
	
}
