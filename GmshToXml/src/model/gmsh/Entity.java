package model.gmsh;

import model.ElementType;

public abstract class Entity {
	
	protected int tag;
	protected PhysicalName[] physicalNames;
	private GmshNode[] nodeList;
	private GmshElement[] elementList;
	private ElementType elementType;
	
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public PhysicalName[] getPhysicalNames() {
		return physicalNames;
	}
	public void setPhysicalNames(PhysicalName[] physicalNames) {
		this.physicalNames = physicalNames;
	}
	public GmshNode[] getNodeList() {
		return nodeList;
	}
	public void setNodeList(GmshNode[] nodeList) {
		this.nodeList = nodeList;
	}
	public void setNode(int position, GmshNode node) {
		this.nodeList[position] = node;
	}
	public ElementType getElementType() {
		return elementType;
	}
	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}
	public void setElement(int position, GmshElement element) {
		this.elementList[position] = element;
	}
	public GmshElement[] getElementList() {
		return elementList;
	}
	public void setElementList(GmshElement[] elementList) {
		this.elementList = elementList;
	}
	
	
}
