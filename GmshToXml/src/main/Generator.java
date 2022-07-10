package main;

import model.Model;
import reader.Reader;
import writter.XmlWritter;

public class Generator {

	public static void main(String[] args) throws Exception {
		Model model = new Model();
		model.fillModel();
		
		List list = new List();
		model.setMasterDofs(list.getMasterDofs());
		model.setRestraint(list.getRestraint());
		model.putRestraints();
		model.putMasterDofs();
		
		XmlWritter writter = new XmlWritter();
		writter.writeXml(model, Reader.outputFilePath);

	}

}
