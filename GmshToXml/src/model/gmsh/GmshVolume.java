package model.gmsh;

public class GmshVolume extends Entity {

	public double minX;
	public double minY;
	public double minZ;
	public double maxX;
	public double maxY;
	public double maxZ;
	public GmshSurface[] surfaces;
	
	public GmshVolume() {
		
	}
	
	public GmshVolume(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, int numSurfaces, int numPhysicalNames) {
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
		this.surfaces = new GmshSurface[numSurfaces];
		this.physicalNames = new PhysicalName[numPhysicalNames];
	}
	
}
