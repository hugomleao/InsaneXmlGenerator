package model.restraints;

import model.Node;

public class XYDirectionRestraint extends Restraint{

	private double x;
	private double y;

	public XYDirectionRestraint(double x, double y, String restraint) {
		this.x = x;
		this.y = y;
		this.setRestraint(restraint);
	}
	
	@Override
	public void applyRestraint(Node node) {
		if (node.getX() == this.x && node.getY() == this.y) {
			node.setRestraints(this.getRestraint());
		}
		
	}
	
}
