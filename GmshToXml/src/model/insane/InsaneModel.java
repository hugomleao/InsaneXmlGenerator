package model.insane;

import java.util.ArrayList;

public class InsaneModel {
	private ArrayList<InsaneNode> nodeList = new ArrayList<>();
	private ArrayList<InsaneElement> elementList = new ArrayList<>();
	
	private AnalysisModel analysisModel;
	private ConstitutiveModel constitutiveModel;
	
	public InsaneModel() {
		
	}
	

	public ArrayList<InsaneNode> getNodeList() {
		return nodeList;
	}

	public void setNodeList(ArrayList<InsaneNode> nodeList) {
		this.nodeList = nodeList;
	}

	public ArrayList<InsaneElement> getElementList() {
		return elementList;
	}

	public void setElementList(ArrayList<InsaneElement> elementList) {
		this.elementList = elementList;
	}

	public AnalysisModel getAnalysisModel() {
		return analysisModel;
	}

	public void setAnalysisModel(AnalysisModel analysisModel) {
		this.analysisModel = analysisModel;
	}

	public ConstitutiveModel getConstitutiveModel() {
		return constitutiveModel;
	}

	public void setConstitutiveModel(ConstitutiveModel constitutiveModel) {
		this.constitutiveModel = constitutiveModel;
	}
	
	

}
