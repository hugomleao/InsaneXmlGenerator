package model.restraints;

import model.Node;

public class XDirectionRestraintFromAtoB extends Restraint{

	private double x;
	private double from;
	private double to;

	public XDirectionRestraintFromAtoB(double x, String restraint, double from, double to) {
		this.x = x;
		this.from = from;
		this.to = to;
		this.setRestraint(restraint);
	}
	@Override
	public void applyRestraint(Node node) {
		if (node.getX() == this.x) {
			double y = node.getY();
			if (y >= from && y <= to) {
				node.setRestraints(this.getRestraint());
			}
		}
		
	}
	
}
