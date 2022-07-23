package writter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import model.insane.InsaneElement;
import model.insane.InsaneModel;
import model.insane.InsaneNode;

public class XmlWritter {

	public void writeXml(InsaneModel model, String path) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			//
			//
			String xmlPartFile = path.replace(".xml", ".xmlpart");
			Document part = docBuilder.parse(new File(xmlPartFile));
			//
			//
			Document doc = docBuilder.newDocument();
			doc.setXmlVersion("1.1");
			doc.setXmlStandalone(true);

			Element insaneSchema = doc.createElement("Insane");
			insaneSchema.setAttribute("xmlns", "http://www.dees.ufmg.br");
			insaneSchema.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			insaneSchema.setAttribute("xsi:schemaLocation", "http://www.dees.ufmg.br insane.xsd");
			doc.appendChild(insaneSchema);

			this.setFromSourceToDestination(part, doc,"Solution");
			this.setFromSourceToDestination(part, doc,"Model");
			this.setNodesList(doc, model);
			this.setElementsList(doc, model);
			this.setFromSourceToDestination(part, doc, "LoadingList");
			this.setFromSourceToDestination(part, doc, "ScalarFunctions");
			this.setFromSourceToDestination(part, doc, "LoadCombinations");

			FileOutputStream output = new FileOutputStream(path);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(output);

			transformer.transform(source, result);

		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void setFromSourceToDestination(Document source, Document destination, String tagName) {
		Node insaneSchema = destination.getElementsByTagName("Insane").item(0);
		Node solSrc = source.getElementsByTagName(tagName).item(0);
		Node solDest = destination.importNode(solSrc, true);
		insaneSchema.appendChild(solDest);
	}
	
	private void setNodesList(Document doc, InsaneModel model) {
		Node xmlModel = doc.getElementsByTagName("Model").item(0);
		xmlModel.appendChild(getXmlNodesList(doc, model));
	}
	
	private void setElementsList(Document doc, InsaneModel model) {
		Node xmlModel = doc.getElementsByTagName("Model").item(0);
		xmlModel.appendChild(getXmlElementsList(doc, model));
	}

	private Element getXmlNode(Document doc, InsaneNode node) {
		Element elmNode = doc.createElement("Node");
		elmNode.setAttribute("label", node.getXmlLabel());
		Element coords = doc.createElement("Coord");
		coords.setTextContent(node.getXmlCoords());
		elmNode.appendChild(coords);
		Element nodeValues = doc.createElement("NodeValues");
		Element Dofs = doc.createElement("DOFLabels");
		Dofs.setTextContent(node.getDOFLabels());
		nodeValues.appendChild(Dofs);
		Element restraints = doc.createElement("Restraints");
		restraints.setTextContent(node.getRestraints());
		nodeValues.appendChild(restraints);
		if (node.getMasterDofs() != null) {
			Element masterDofs = doc.createElement("MasterDOFs");
			masterDofs.setTextContent(node.getMasterDofs());
			nodeValues.appendChild(masterDofs);
		}
		elmNode.appendChild(nodeValues);
		return elmNode;
	}

	private Element getXmlNodesList(Document doc, InsaneModel model) {
		Element nodeList = doc.createElement("NodeList");
		Iterator<InsaneNode> ite = model.getNodeList().iterator();
		while (ite.hasNext()) {
			InsaneNode node = ite.next();
			nodeList.appendChild(this.getXmlNode(doc, node));
		}
		return nodeList;
	}

	private Element getXmlElement(Document doc, InsaneElement element) {
		Element xmlElm = doc.createElement("Element");
		xmlElm.setAttribute("class", element.getInsaneClass());
		xmlElm.setAttribute("label", element.getInsaneLabel());
		Element incidence = doc.createElement("Incidence");
		incidence.setTextContent(element.getInsaneIncidence());
		xmlElm.appendChild(incidence);
		Element am = doc.createElement("AnalysisModel");
		am.setTextContent(element.getInsaneAnalysisModel());
		xmlElm.appendChild(am);
		Element io = doc.createElement("IntegrationOrder");
		io.setTextContent(element.getIntegrationOrder());
		xmlElm.appendChild(io);
		Element cm = doc.createElement("ConstitutiveModel");
		cm.setTextContent(element.getInsaneConstitutiveModel());
		xmlElm.appendChild(cm);
		Element deg = doc.createElement("ElmDegenerations");
		deg.setTextContent(element.getDegeneration());
		xmlElm.appendChild(deg);
		return xmlElm;
	}

	private Element getXmlElementsList(Document doc, InsaneModel model) {
		Element elmList = doc.createElement("ElementList");
		Iterator<InsaneElement> ite = model.getElementList().iterator();
		while (ite.hasNext()) {
			InsaneElement element = ite.next();
			elmList.appendChild(this.getXmlElement(doc, element));
		}
		return elmList;
	}

}
