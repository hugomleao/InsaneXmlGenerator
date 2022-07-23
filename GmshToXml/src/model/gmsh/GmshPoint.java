package model.gmsh;

public class GmshPoint extends Entity {
	public double x;
	public double y;
	public double z;
	
	public GmshPoint() {
		
	}
	
	public GmshPoint(int tag, double x, double y, double z, PhysicalName[] pn) {
		this.tag = tag;
		this.x = x;
		this.y = y;
		this.z = z;
		this.physicalNames = pn;
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
	
	@Override
	public String toString() {
		return "PointTag:" + this.tag;
	}
}
