package model.restraints;

import model.Node;

public class YDirectionRestraintFromAtoB extends Restraint{

	private double y;
	private double from;
	private double to;
	
	public YDirectionRestraintFromAtoB(double y, String restraint, double from, double to) {
		this.y = y;
		this.from = from;
		this.to = to;
		this.setRestraint(restraint);
	}

	@Override
	public void applyRestraint(Node node) {
		if (node.getY() == this.y) {
			double x = node.getX();
			if (x >= from && x <= to) {
				node.setRestraints(this.getRestraint());
			}
		}
		
	}
	
}
