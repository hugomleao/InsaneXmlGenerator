package main;

import model.ModelManager;
import reader.Reader;
import writter.XmlWritter;

public class Generator {

	public static void main(String[] args) throws Exception {
		
		//String inputFile = System.getProperty("user.home") + "\\Desktop\\Mesh.geo";
		String inputFile = Reader.readFile();
		
		ModelManager manager = new ModelManager(inputFile);
		manager.createMshFile();
		manager.fillGmshModel();
		manager.fillInsaneModel();
		
		XmlWritter writter = new XmlWritter(manager.getInfoFilePath(), manager.getInsaneModel());
		writter.writeXml();

	}

}
