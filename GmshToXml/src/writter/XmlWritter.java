package writter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import model.FemElement;
import model.Model;
import model.Node;

public class XmlWritter {
	
	public static final String NEW_LINE = "\n";

	public static final String SPACE = "    ";

	
	public void writeXml(Model model, String path) {
		File outputFile = new File(path);
		XMLOutputFactory xmlOutputFacotory = XMLOutputFactory.newInstance();
		try {
			
			
			XMLStreamWriter xmlw = xmlOutputFacotory.createXMLStreamWriter(new FileOutputStream(outputFile));
			xmlw.writeStartDocument();
			xmlw.writeCharacters(NEW_LINE);

			//The root element
			xmlw.writeStartElement("root");
			xmlw.writeCharacters(NEW_LINE);
			xmlw.writeCharacters(NEW_LINE);
			
			
			this.writeNodes(xmlw, model);
			this.writeElements(xmlw, model);
			
			
			
			//End root element
			xmlw.writeEndElement();

			// End the XML document
			xmlw.writeEndDocument();

			// Close the XMLStreamWriter to free up resources
			xmlw.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void writeNodeCoords(XMLStreamWriter xmlw, Node node) throws XMLStreamException {
		//Writing the node coords
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeStartElement("Coord");
		xmlw.writeCharacters(node.getCoords());
		//End the node coords
		xmlw.writeEndElement();
		xmlw.writeCharacters(NEW_LINE);
	}
	
	private void writeNodeValues(XMLStreamWriter xmlw, Node node) throws XMLStreamException {
		//Writing node values
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeStartElement("NodeValues");
		xmlw.writeCharacters(NEW_LINE);
		
		this.writeNodeDOFLabels(xmlw, node);
		this.writeNodeRestraints(xmlw, node);
		this.writeNodeMasterDofs(xmlw, node);
		this.writeNodeStiffness(xmlw, node);
		this.writeNodePreDisplacements(xmlw, node);
		
		//End of node values
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeEndElement();
		xmlw.writeCharacters(NEW_LINE);
	}
	
	private void writeNodeDOFLabels(XMLStreamWriter xmlw, Node node) throws XMLStreamException {
		//Writing the DOFLabels
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeStartElement("DOFLabels");
		xmlw.writeCharacters("Dx Dy PF");
	//End the node DOFLabels
		xmlw.writeEndElement();
		xmlw.writeCharacters(NEW_LINE);
	}
	
	private void writeNodeRestraints(XMLStreamWriter xmlw, Node node) throws XMLStreamException {
		//Writing the Restraints
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeStartElement("Restraints");
		xmlw.writeCharacters(node.getRestraints());
		//End the node Restraints
		xmlw.writeEndElement();
		xmlw.writeCharacters(NEW_LINE);
	}
	
	private void writeNodeMasterDofs(XMLStreamWriter xmlw, Node node) throws XMLStreamException {
		//Writing the MasterDofs
		if (node.getMasterDofs() != null) {
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeStartElement("MasterDOFs");
			xmlw.writeCharacters(node.getMasterDofs());
			//End the node MasterDofs
			xmlw.writeEndElement();
			xmlw.writeCharacters(NEW_LINE);
		}
	}
	
	private void writeNodeStiffness(XMLStreamWriter xmlw, Node node) throws XMLStreamException {
		//Writing the Stiffness
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeStartElement("Stiffness");
		xmlw.writeCharacters("0.0 0.0 0.0");
		//End the node Stiffness
		xmlw.writeEndElement();
		xmlw.writeCharacters(NEW_LINE);
	}
	
	private void writeNodePreDisplacements(XMLStreamWriter xmlw, Node node) throws XMLStreamException {
		//Writing the PreDisplacements
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeStartElement("PreDisplacements");
		xmlw.writeCharacters("0.0 0.0 0.0");
		//End the node PreDisplacements
		xmlw.writeEndElement();
		xmlw.writeCharacters(NEW_LINE);
	}
	
	private void writeNode(XMLStreamWriter xmlw, Node node) throws XMLStreamException {
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		//Writing the node label
			xmlw.writeStartElement("Node");
			xmlw.writeAttribute("label", node.getId());
			xmlw.writeCharacters(NEW_LINE);
		
			this.writeNodeCoords(xmlw, node);
			this.writeNodeValues(xmlw, node);
			
			
		//End of node
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeEndElement();
			xmlw.writeCharacters(NEW_LINE);
	}
	
	private void writeNodes(XMLStreamWriter xmlw, Model model) throws XMLStreamException {
		//Writing nodes list
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeStartElement("NodeList");
		xmlw.writeCharacters(NEW_LINE);
		Iterator<Node> ite = model.getNodes().iterator();
		while (ite.hasNext()) {
			Node node = ite.next();
			this.writeNode(xmlw, node);
		}
		//End of node List
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeEndElement();
		xmlw.writeCharacters(NEW_LINE);
		
	}
	
	private void writeElements(XMLStreamWriter xmlw, Model model) throws XMLStreamException {
		//Writing elements list
				xmlw.writeCharacters(SPACE);
				xmlw.writeCharacters(SPACE);
				xmlw.writeStartElement("ElementList");
				xmlw.writeCharacters(NEW_LINE);
				Iterator<FemElement> ite = model.getElements().iterator();
				while (ite.hasNext()) {
					FemElement element = ite.next();
					this.writeElement(xmlw, element);
				}
				//End of element List
				xmlw.writeCharacters(SPACE);
				xmlw.writeCharacters(SPACE);
				xmlw.writeEndElement();
				xmlw.writeCharacters(NEW_LINE);
	}

	private void writeElement(XMLStreamWriter xmlw, FemElement element) throws XMLStreamException {
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		xmlw.writeCharacters(SPACE);
		//Writing the element class and label
			xmlw.writeStartElement("Element");
			xmlw.writeAttribute("class", "ParametricElement.Triangular.T3");
			xmlw.writeAttribute("label", String.valueOf(element.getId()));
			xmlw.writeCharacters(NEW_LINE);
		//Writing the element incidence
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeStartElement("Incidence");
			xmlw.writeCharacters(element.getIncidence());
		//End the element incidence
			xmlw.writeEndElement();
			xmlw.writeCharacters(NEW_LINE);
		//Writing the element analysis model
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeStartElement("AnalysisModel");
			xmlw.writeCharacters("PlaneStressPhaseFieldStaggeredSolver");
		//End the element analysis model
			xmlw.writeEndElement();
			xmlw.writeCharacters(NEW_LINE);
		//Writing the element integration order
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeStartElement("IntegrationOrder");
			xmlw.writeCharacters("1 0 0");
		//End the element integration order
			xmlw.writeEndElement();
			xmlw.writeCharacters(NEW_LINE);
		//Writing the element constitutive model
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeStartElement("ConstitutiveModel");
			xmlw.writeCharacters("StgPfIsotropicConstModel");
		//End the element constitutive model
			xmlw.writeEndElement();
			xmlw.writeCharacters(NEW_LINE);
		//Writing the element section
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeStartElement("ElmDegenerations");
			xmlw.writeCharacters(String.valueOf(element.getRegion().getLabel()));
		//End the element section
			xmlw.writeEndElement();
			xmlw.writeCharacters(NEW_LINE);
		//End of element
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeCharacters(SPACE);
			xmlw.writeEndElement();
			xmlw.writeCharacters(NEW_LINE);
		
	}

	
	}


