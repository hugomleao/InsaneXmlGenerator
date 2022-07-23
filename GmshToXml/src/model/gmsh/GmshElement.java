package model.gmsh;

public class GmshElement {
	private int tag;
	private GmshNode[] nodes;
	private Entity entity;
	
	public GmshElement() {
		
	}
	
	public GmshElement(int tag, GmshNode[] nodes) {
		this.tag = tag;
		this.nodes = nodes;
	}
	
	public GmshElement(int tag, int numberOfNodes) {
		this.tag = tag;
		this.nodes = new GmshNode[numberOfNodes];
	}
	
	public void setNode(int position, GmshNode node) {
		this.nodes[position] = node;
	}
	
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public GmshNode[] getNodes() {
		return nodes;
	}
	public void setNodes(GmshNode[] nodes) {
		this.nodes = nodes;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	
	
}
