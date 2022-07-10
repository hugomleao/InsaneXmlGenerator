package model.restraints;

import model.Node;

public class YDirectionRestraint extends Restraint{

	private double y;
	
	public YDirectionRestraint(double y, String restraint) {
		this.y = y;
		this.setRestraint(restraint);
	}

	@Override
	public void applyRestraint(Node node) {
		if (node.getY() == this.y) {
			node.setRestraints(this.getRestraint());
		}
		
	}
	
}
