package main;

import model.ModelManager;
import model.insane.AnalysisModel;
import model.insane.ConstitutiveModel;
import writter.XmlWritter;

public class Generator {

	public static void main(String[] args) throws Exception {
		
		ModelManager manager = new ModelManager("C:\\Users\\hugom\\Desktop\\Mesh.msh");
		manager.fillGmshModel();
		manager.fillInsaneModel(ConstitutiveModel.STG_PF_ISOTROPIC_CONST_MODEL, AnalysisModel.PLANE_STRESS_PHASE_FIELD_STAGGERED_SOLVER);
		

		XmlWritter writter = new XmlWritter();
		writter.writeXml(manager.getInsaneModel(), "C:\\Users\\hugom\\Desktop\\Mesh.xml");

	}

}
