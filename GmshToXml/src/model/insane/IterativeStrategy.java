package model.insane;

public class IterativeStrategy {

	private String directionControl;
	private InsaneNode nodeControl;
	private double loadFactor;
	
	
	public IterativeStrategy(String directionControl, InsaneNode nodeControl, double loadFactor) {
		this.nodeControl = nodeControl;
		this.directionControl = directionControl;
		this.loadFactor = loadFactor;
	}

	public InsaneNode getNodeControl() {
		return nodeControl;
	}

	public void setNodeControl(InsaneNode nodeControl) {
		this.nodeControl = nodeControl;
	}

	public String getDirectionControl() {
		return directionControl;
	}

	public void setDirectionControl(String directionControl) {
		this.directionControl = directionControl;
	}

	public double getLoadFactor() {
		return loadFactor;
	}
	
	public String getLoadFactorToString() {
		return String.valueOf(loadFactor);
	}

	public void setLoadFactor(double loadFactor) {
		this.loadFactor = loadFactor;
	}
	
	
	
	
	
	
}
