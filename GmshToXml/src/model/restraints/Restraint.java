package model.restraints;

import model.Node;

public abstract class Restraint {
	
	private String restraint;
	
	public abstract void applyRestraint(Node node);
	
	public String getRestraint() {
		return restraint;
	}

	public void setRestraint(String restraint) {
		this.restraint = restraint;
	}


}
