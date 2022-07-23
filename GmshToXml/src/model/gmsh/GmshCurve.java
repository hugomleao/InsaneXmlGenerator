package model.gmsh;

public class GmshCurve extends Entity {
	public double minX;
	public double minY;
	public double minZ;
	public double maxX;
	public double maxY;
	public double maxZ;
	public GmshPoint[] points;
	
	public GmshCurve() {
		
	}
	
	public GmshCurve(int tag, double minX, double minY, double minZ, 
			double maxX, double maxY, double maxZ,
			GmshPoint[] points, PhysicalName[] physicalNames) {
		this.tag = tag;
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
		this.points = points;
		this.physicalNames = physicalNames;
	}
	
	public double getMinX() {
		return minX;
	}
	public void setMinX(double minX) {
		this.minX = minX;
	}
	public double getMinY() {
		return minY;
	}
	public void setMinY(double minY) {
		this.minY = minY;
	}
	public double getMinZ() {
		return minZ;
	}
	public void setMinZ(double minZ) {
		this.minZ = minZ;
	}
	public double getMaxX() {
		return maxX;
	}
	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}
	public double getMaxY() {
		return maxY;
	}
	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}
	public double getMaxZ() {
		return maxZ;
	}
	public void setMaxZ(double maxZ) {
		this.maxZ = maxZ;
	}
	public GmshPoint[] getPoints() {
		return points;
	}
	public void setPoints(GmshPoint[] points) {
		this.points = points;
	}
	
	@Override
	public String toString() {
		return "CurveTag: " + this.tag;
	}
}
