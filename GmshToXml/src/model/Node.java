package model;

public class Node {

	private String id;
	private double x;
	private double y;
	private double z;
	private String restraints;
	private String masterDofs;
	
	
	public String getMasterDofs() {
		return masterDofs;
	}

	public void setMasterDofs(String masterDofs) {
		this.masterDofs = masterDofs;
	}

	public String getRestraints() {
		return restraints;
	}

	public void setRestraints(String restraints) {
		this.restraints = restraints;
	}

	public Node(String id, double x, double y, double z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.restraints = "false false false";
	}
	
	public Node(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String getCoords() {
		return this.x + " " + this.y + " " + this.z;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
