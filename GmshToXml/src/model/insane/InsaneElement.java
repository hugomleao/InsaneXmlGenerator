package model.insane;

import model.Dictionary;
import model.ElementType;
import model.gmsh.GmshElement;
import model.gmsh.PhysicalName;

public class InsaneElement {
	int label;
	InsaneNode[] incidence;
	ConstitutiveModel constitutiveModel;
	AnalysisModel analysisModel;
	ElementType elementType;
	String integrationOrder;
	String degeneration;
	
	public InsaneElement(int label, GmshElement gmshElement, ConstitutiveModel constitutiveModel, AnalysisModel analysisModel) throws Exception{
		this.label = label;
		this.constitutiveModel = constitutiveModel;
		this.analysisModel = analysisModel;
		this.integrationOrder = gmshElement.getEntity().getElementType().getIntegrationOrder();
		this.elementType = gmshElement.getEntity().getElementType();
		setElementDegeneration(gmshElement);
	}
	
	private void setElementDegeneration(GmshElement gmshElement) throws Exception {
		PhysicalName[] pnList = gmshElement.getEntity().getPhysicalNames();
		boolean find = false;
		for(int i = 0; i < pnList.length; i++) {
			String degeneration = pnList[i].getName();
			if(degeneration.startsWith(Dictionary.DEGENERATION) && !find) {
				find = true;
				int initialIndex = degeneration.indexOf(":");
				degeneration = degeneration.substring(initialIndex + 1).trim();
				this.degeneration = degeneration;
			} else if (degeneration.startsWith(Dictionary.DEGENERATION) && find) {
				throw new Exception();
			}
		}
	}
	
	public String getInsaneClass() {
		return elementType.getInsaneElement();
	}
	
	public String getInsaneLabel() {
		return Integer.toString(label);
	}
	
	public String getInsaneIncidence() {
		String ans = "";
		for(int i = 0; i < incidence.length; i++) {
			ans += incidence[i].getLabel() + " ";
		}
		ans = ans.substring(0, ans.length() - 1);
		return ans;
	}
	
	public String getInsaneAnalysisModel() {
		return analysisModel.getInsaneName();
	}
	
	public String getIntegrationOrder() {
		return integrationOrder;
	}
	
	public String getInsaneConstitutiveModel() {
		return constitutiveModel.getInsaneName();
	}
	
	public String getDegeneration() {
		return degeneration;
	}

	public void setIncidence(InsaneNode[] incidence) {
		this.incidence = incidence;
	}
	
	
	

}
