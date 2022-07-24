package model.gmsh;

import java.util.HashMap;

public class GmshModel {

	private HashMap<Integer, PhysicalName> physicalNames = new HashMap<>();

	// Entities
	private GmshPoint[] points;
	private GmshCurve[] curves;
	private GmshSurface[] surfaces;
	private GmshVolume[] volumes;

	// Nodes and Elements
	private GmshNode[] nodes;
	private GmshElement[] elements;
	
	public GmshModel() {
		
	}
	
	/**
	 * Returns the entity object.
	 * 
	 * @param entityDim The entity dimension.
	 * @param entityTag The entity tag.
	 * @return
	 */
	public Entity getEntity(int entityDim, int entityTag) {
		Entity[] entities = null;
		if (entityDim == 0) {
			entities = this.getPoints();
		} else if (entityDim == 1) {
			entities = this.getCurves();
		} else if (entityDim == 2) {
			entities = this.getSurfaces();
		} else if (entityDim == 3) {
			entities = this.getVolumes();
		}
		return entities[entityTag - 1];
	}
	
	public PhysicalName getPhysicalNameByTag(int tag) {
		return this.physicalNames.get(tag);
	}

	/**
	 * Sets the physicalNames array.
	 * 
	 * @param physicalNames
	 */
	public void setPhysicalNameByTag(int tag, PhysicalName physicalName) {
		this.physicalNames.put(tag, physicalName);
	}
	
	/**
	 * 
	 * @return List of Nodes from GmshModel.
	 */
	public GmshNode[] getNodes() {
		return nodes;
	}
	
	public void setNodes(GmshNode[] nodes) {
		this.nodes = nodes;
	}
	
	public GmshNode getNode(int index) {
		return this.nodes[index];
	}
	
	public void setNode(int index, GmshNode node) {
		this.nodes[index] = node;
	}

	/**
	 * 
	 * @return List of Elements from GmshModel.
	 */
	public GmshElement[] getElements() {
		return elements;
	}
	
	public void setElement(int index, GmshElement element) {
		this.elements[index] = element;
	}

	/**
	 * 
	 * @return The list of GmshPoints from GmshModel.
	 */
	public GmshPoint[] getPoints() {
		return points;
	}

	/**
	 * Sets the list of points in GmshModel.
	 * 
	 * @param points List of GmshPoints
	 */
	public void setPoints(GmshPoint[] points) {
		this.points = points;
	}
	
	public GmshPoint getPoint(int index) {
		return this.points[index];
	}

	public GmshCurve[] getCurves() {
		return curves;
	}

	public void setCurves(GmshCurve[] curves) {
		this.curves = curves;
	}
	
	public GmshCurve getCurve(int index) {
		return this.curves[index];
	}

	public GmshSurface[] getSurfaces() {
		return surfaces;
	}

	public void setSurfaces(GmshSurface[] surfaces) {
		this.surfaces = surfaces;
	}

	public void setElements(GmshElement[] elements) {
		this.elements = elements;
	}

	public GmshVolume[] getVolumes() {
		return volumes;
	}

	public void setVolumes(GmshVolume[] volumes) {
		this.volumes = volumes;
	}

}
