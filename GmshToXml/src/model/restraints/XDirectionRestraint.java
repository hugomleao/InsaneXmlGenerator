package model.restraints;

import model.Node;

public class XDirectionRestraint extends Restraint{

	private double x;

	public XDirectionRestraint(double x, String restraint) {
		this.x = x;
		this.setRestraint(restraint);
	}
	@Override
	public void applyRestraint(Node node) {
		if (node.getX() == this.x) {
			node.setRestraints(this.getRestraint());
		}
		
	}
	
}
