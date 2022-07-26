package model.insane;

import model.Dictionary;
import model.gmsh.GmshNode;
import model.gmsh.PhysicalName;

public class InsaneNode {
	private int label;
	private double x;
	private double y;
	private double z;
	private String restraints;
	private String masterDofs;
	private String DOFLabels;
	
	public InsaneNode() {
		
	}
	
	public InsaneNode(int label, GmshNode gmshNode) throws Exception {
		this.label = label;
		this.x = gmshNode.getX();
		this.y = gmshNode.getY();
		this.z = gmshNode.getZ();
		this.setNodeRestraint(gmshNode);
		this.setNodeMasterDofs(gmshNode);
	}
	
	public InsaneNode(int label, GmshNode gmshNode, AnalysisModel analysisModel) throws Exception {
		this(label, gmshNode);
		this.DOFLabels = analysisModel.getDofLabels();
	}
	
	private void setNodeRestraint(GmshNode gmshNode) throws Exception {
		PhysicalName[] pnList = gmshNode.getEntity().getPhysicalNames();
		boolean find = false;
		for(int i = 0; i < pnList.length; i++) {
			String restraint = pnList[i].getName();
			if(restraint.startsWith(Dictionary.RESTRAINTS) && !find) {
				find = true;
				int initialIndex = restraint.indexOf(":");
				restraint = restraint.substring(initialIndex + 1).trim();
				this.restraints = restraint;
			} else if (restraint.startsWith(Dictionary.RESTRAINTS) && find) {
				throw new Exception();
			}
		}
		if(!find) {
			this.restraints = "false false false";
		}
	}
	
	private void setNodeMasterDofs(GmshNode gmshNode) throws Exception {
		PhysicalName[] pnList = gmshNode.getEntity().getPhysicalNames();
		boolean find = false;
		for(int i = 0; i < pnList.length; i++) {
			String masterDofs = pnList[i].getName();
			if(masterDofs.startsWith(Dictionary.MASTER_DOFS) && !find) {
				find = true;
				int initialIndex = masterDofs.indexOf(":");
				masterDofs = masterDofs.substring(initialIndex + 1).trim();
				this.masterDofs = masterDofs;
			} else if (masterDofs.startsWith(Dictionary.MASTER_DOFS) && find) {
				throw new Exception();
			}
		}
	}
	
	public String getXmlCoords() {
		return this.x + " " + this.y + " " + this.z;
	}
	
	public String getXmlLabel() {
		return Integer.toString(label);
	}
	
	public int getLabel() {
		return label;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
	public String getRestraints() {
		return restraints;
	}
	public void setRestraints(String restraints) {
		this.restraints = restraints;
	}
	public String getMasterDofs() {
		return masterDofs;
	}
	public void setMasterDofs(String masterDofs) {
		this.masterDofs = masterDofs;
	}

	public String getDOFLabels() {
		return DOFLabels;
	}

	public void setDOFLabels(String dOFLabels) {
		DOFLabels = dOFLabels;
	}
	
	public String getLabelToString() {
		return String.valueOf(label);
	}
	
}
