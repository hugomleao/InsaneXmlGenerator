package model.masterdofs;

import model.Node;

public abstract class MasterDofs {

	private int masterDirection;
	private Node masterNode;
	
	public abstract void applyMasterDof(Node node);
	public abstract String getMasterDofs();

	public Node getMasterNode() {
		return masterNode;
	}

	public void setMasterNode(Node masterNode) {
		this.masterNode = masterNode;
	}
	
	public int getMasterDirection() {
		return masterDirection;
	}
	

	public void setMasterDirection(int direction) {
		this.masterDirection = direction;
	}

	
	

	
}
