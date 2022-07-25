package main;

import model.ModelManager;
import model.insane.AnalysisModel;
import model.insane.ConstitutiveModel;
import reader.Reader;
import writter.XmlWritter;

public class Generator {

	public static void main(String[] args) throws Exception {
		
		//String inputFile = System.getProperty("user.home") + "\\Desktop\\Mesh.geo";
		String inputFile = Reader.readFile();
		
		ModelManager manager = new ModelManager(inputFile);
		manager.createMshFile();
		manager.fillGmshModel();
		manager.fillInsaneModel(ConstitutiveModel.STG_PF_ISOTROPIC_CONST_MODEL, AnalysisModel.PLANE_STRESS_PHASE_FIELD_STAGGERED_SOLVER);
		
		XmlWritter writter = new XmlWritter(manager.getInfoFilePath());
		writter.writeXml(manager.getInsaneModel());

	}

}
