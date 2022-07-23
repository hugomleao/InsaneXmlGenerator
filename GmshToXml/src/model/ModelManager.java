package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import model.gmsh.Entity;
import model.gmsh.GmshCurve;
import model.gmsh.GmshElement;
import model.gmsh.GmshModel;
import model.gmsh.GmshNode;
import model.gmsh.GmshPoint;
import model.gmsh.GmshSurface;
import model.gmsh.GmshVolume;
import model.gmsh.PhysicalName;
import model.insane.AnalysisModel;
import model.insane.ConstitutiveModel;
import model.insane.InsaneElement;
import model.insane.InsaneModel;
import model.insane.InsaneNode;
import reader.Reader;

public class ModelManager {
	private String filePath;
	private GmshModel gmshModel;
	private InsaneModel insaneModel;
	private HashMap<Integer, Integer> nodeLabelInInsane = new HashMap<Integer, Integer>();
	
	public ModelManager(String filePath) {
		this.filePath = filePath;
	}

	public void fillGmshModel() {
		try {
			gmshModel = new GmshModel();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			this.fillGmshPhysicalNames(bufferedReader);
			this.fillGmshEntities(bufferedReader);
			this.fillGmshNodes(bufferedReader);
			this.fillElements(bufferedReader);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillInsaneModel(ConstitutiveModel constitutiveModel, AnalysisModel analysisModel) {
		insaneModel = new InsaneModel();
		insaneModel.setAnalysisModel(analysisModel);
		insaneModel.setConstitutiveModel(constitutiveModel);
		fillInsaneNodeList();
		fillInsaneElementList();
	}

	/**
	 * Fills the PhysicalName of GmshModel list from msh file.
	 * 
	 * @param br The buffered reader of the msh file.
	 */
	private void fillGmshPhysicalNames(BufferedReader br) {
		try {

			Reader.findLineThatStartsWith(br, "$PhysicalNames");
			int numPhysicalNames;
			numPhysicalNames = Integer.parseInt(br.readLine());
			gmshModel.setPhysicalNames(new PhysicalName[numPhysicalNames]);
			for (int i = 0; i < numPhysicalNames; i++) {
				String[] split = br.readLine().split(" ", 3);
				int dimension = Integer.parseInt(split[0]);
				int physicalTag = Integer.parseInt(split[1]);
				String name = split[2].replace("\"", "");
				PhysicalName pn = new PhysicalName(dimension, physicalTag, name);
				gmshModel.setPhysicalName(physicalTag - 1, pn);
			}

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fills the Entities of GmshModel list from msh file.
	 * 
	 * @param br The buffered reader of the msh file.
	 */
	private void fillGmshEntities(BufferedReader br) {
		try {

			Reader.findLineThatStartsWith(br, "$Entities");
			String[] split;
			split = br.readLine().split(" ");
			gmshModel.setPoints(new GmshPoint[Integer.parseInt(split[0])]);
			gmshModel.setCurves(new GmshCurve[Integer.parseInt(split[1])]);
			gmshModel.setSurfaces(new GmshSurface[Integer.parseInt(split[2])]);
			gmshModel.setVolumes(new GmshVolume[Integer.parseInt(split[3])]);
			fillGmshPoints(br);
			fillGmshCurves(br);
			fillGmshSurface(br);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fills the Point list of GmshModel from msh file.
	 * 
	 * @param br The buffered reader of the msh file.
	 * @throws Exception
	 */
	private void fillGmshPoints(BufferedReader br) {
		try {

			GmshPoint[] gmshPoints = gmshModel.getPoints();
			for (int i = 0; i < gmshPoints.length; i++) {
				String[] split;
				split = br.readLine().split(" ");
				int tag = Integer.parseInt(split[0]);
				double x = Double.parseDouble(split[1]);
				double y = Double.parseDouble(split[2]);
				double z = Double.parseDouble(split[3]);
				PhysicalName[] pn = new PhysicalName[Integer.parseInt(split[4])];
				for (int j = 0; j < pn.length; j++) {
					int physicalTag = Integer.parseInt(split[5 + j]);
					pn[j] = gmshModel.getPhysicalName(physicalTag - 1);
				}
				GmshPoint p = new GmshPoint(tag, x, y, z, pn);
				gmshPoints[tag - 1] = p;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fills the Curve list of GmshModel from msh file.
	 * 
	 * @param br The buffered reader of the msh file.
	 * @throws Exception
	 */
	private void fillGmshCurves(BufferedReader br) {
		try {

			GmshCurve[] gmshCurves = gmshModel.getCurves();
			for (int i = 0; i < gmshCurves.length; i++) {
				String[] split = br.readLine().split(" ");
				int tag = Integer.parseInt(split[0]);
				double minX = Double.parseDouble(split[1]);
				double minY = Double.parseDouble(split[2]);
				double minZ = Double.parseDouble(split[3]);
				double maxX = Double.parseDouble(split[4]);
				double maxY = Double.parseDouble(split[5]);
				double maxZ = Double.parseDouble(split[6]);
				PhysicalName[] pn = new PhysicalName[Integer.parseInt(split[7])];
				for (int j = 0; j < pn.length; j++) {
					int physicalTag = Integer.parseInt(split[8 + j]);
					pn[j] = gmshModel.getPhysicalName(physicalTag - 1);
				}
				GmshPoint[] p = new GmshPoint[Integer.parseInt(split[8 + pn.length])];
				for (int j = 0; j < p.length; j++) {
					int pointTag = Math.abs(Integer.parseInt(split[9 + pn.length + j]));
					p[j] = gmshModel.getPoint(pointTag - 1);
				}
				GmshCurve c = new GmshCurve(tag, minX, minY, minZ, maxX, maxY, maxZ, p, pn);
				gmshCurves[tag - 1] = c;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fills the Surface list of GmshModel from msh file.
	 * 
	 * @param br The buffered reader of the msh file.
	 */
	private void fillGmshSurface(BufferedReader br) {
		try {

			GmshSurface[] gmshSurfs = gmshModel.getSurfaces();
			for (int i = 0; i < gmshSurfs.length; i++) {
				String[] split = br.readLine().split(" ");
				int tag = Integer.parseInt(split[0]);
				double minX = Double.parseDouble(split[1]);
				double minY = Double.parseDouble(split[2]);
				double minZ = Double.parseDouble(split[3]);
				double maxX = Double.parseDouble(split[4]);
				double maxY = Double.parseDouble(split[5]);
				double maxZ = Double.parseDouble(split[6]);
				PhysicalName[] pn = new PhysicalName[Integer.parseInt(split[7])];
				for (int j = 0; j < pn.length; j++) {
					int physicalTag = Integer.parseInt(split[8 + j]);
					pn[j] = gmshModel.getPhysicalName(physicalTag - 1);
				}
				GmshCurve[] c = new GmshCurve[Integer.parseInt(split[8 + pn.length])];
				for (int j = 0; j < c.length; j++) {
					int curveTag = Math.abs(Integer.parseInt(split[9 + pn.length + j]));
					c[j] = gmshModel.getCurve(curveTag - 1);
				}
				GmshSurface s = new GmshSurface(tag, minX, minY, minZ, maxX, maxY, maxZ, pn, c);
				gmshSurfs[tag - 1] = s;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fills the Nodes list of GmshModel from msh file.
	 * 
	 * @param br The buffered reader of the msh file.
	 */
	private void fillGmshNodes(BufferedReader br) {
		try {

			Reader.findLineThatStartsWith(br, "$Nodes");
			String[] split;
			split = br.readLine().split(" ");
			int numEntityBlocks = Integer.parseInt(split[0]);
			int numNodes = Integer.parseInt(split[1]);
			gmshModel.setNodes(new GmshNode[numNodes]);
			// int minNodeTag = Integer.parseInt(split[2]);
			// int maxNodeTag = Integer.parseInt(split[3]);
			for (int i = 0; i < numEntityBlocks; i++) {
				split = br.readLine().split(" ");
				int entityDim = Integer.parseInt(split[0]);
				int entityTag = Integer.parseInt(split[1]);
				Entity entity = gmshModel.getEntity(entityDim, entityTag);
				// int parametric = Integer.parseInt(split[2]);
				int numNodesInBlock = Integer.parseInt(split[3]);
				entity.setNodeList(new GmshNode[numNodesInBlock]);
				int[] nodeTags = new int[numNodesInBlock];
				for (int j = 0; j < numNodesInBlock; j++) {
					nodeTags[j] = Integer.parseInt(br.readLine());
				}
				for (int j = 0; j < numNodesInBlock; j++) {
					split = br.readLine().split(" ");
					double x = Double.parseDouble(split[0]);
					double y = Double.parseDouble(split[1]);
					double z = Double.parseDouble(split[2]);
					GmshNode node = new GmshNode(entity, nodeTags[j], x, y, z);
					entity.setNode(j, node);
					gmshModel.setNode(nodeTags[j] - 1, node);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fills the Elements from msh file.
	 * 
	 * @param br The buffered reader of the msh file.
	 */
	private void fillElements(BufferedReader br) {
		try {

			Reader.findLineThatStartsWith(br, "$Elements");
			String[] split = br.readLine().split(" ");
			int numEntityBlocks = Integer.parseInt(split[0]);
			int numElements = Integer.parseInt(split[1]);
			gmshModel.setElements(new GmshElement[numElements]);
			// int minElementTag = Integer.parseInt(split[2]);
			// int maxElementTag = Integer.parseInt(split[3]);
			for (int i = 0; i < numEntityBlocks; i++) {
				split = br.readLine().split(" ");
				int entityDim = Integer.parseInt(split[0]);
				int entityTag = Integer.parseInt(split[1]);
				Entity entity = gmshModel.getEntity(entityDim, entityTag);
				ElementType elmType = ElementType.BY_TAG.get(Integer.parseInt(split[2]));
				entity.setElementType(elmType);
				int numElementsInBlock = Integer.parseInt(split[3]);
				entity.setElementList(new GmshElement[numElementsInBlock]);
				for (int j = 0; j < numElementsInBlock; j++) {
					split = br.readLine().split(" ");
					int elmTag = Integer.parseInt(split[0]);
					GmshElement elm = new GmshElement(elmTag, elmType.getNumberOfNodes());
					for (int k = 0; k < elmType.getNumberOfNodes(); k++) {
						int nodeTag = Integer.parseInt(split[k + 1]);
						GmshNode node = gmshModel.getNode(nodeTag - 1);
						elm.setNode(k, node);
					}
					elm.setEntity(entity);
					entity.setElement(j, elm);
					gmshModel.setElement(elmTag - 1, elm);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fillInsaneNodeList() {
		try {

			GmshNode[] gmshNodes = gmshModel.getNodes();
			for (int i = 0; i < gmshNodes.length; i++) {
				GmshNode gmshNode = gmshNodes[i];
				int nodeLabel = insaneModel.getNodeList().size() + 1;
				InsaneNode node = new InsaneNode(nodeLabel, gmshNode, insaneModel.getAnalysisModel());
				insaneModel.getNodeList().add(node);
				fillNodesMap(nodeLabel, gmshNode.getTag());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillInsaneElementList() {
		try {

			GmshElement[] gmshElms = gmshModel.getElements();
			for (int i = 0; i < gmshElms.length; i++) {
				GmshElement gmshElm = gmshElms[i];
				if (!gmshElm.getEntity().getElementType().getInsaneElement().isEmpty()) {
					int elementLabel = insaneModel.getElementList().size() + 1;
					InsaneElement insaneElm = new InsaneElement(elementLabel, gmshElm,
							insaneModel.getConstitutiveModel(),insaneModel.getAnalysisModel());
					setElementIncidence(insaneElm, gmshElm);
					insaneModel.getElementList().add(insaneElm);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setElementIncidence(InsaneElement insaneElement, GmshElement gmshElement) {
	GmshNode[] gmshIncidence = gmshElement.getNodes();
	InsaneNode[] insaneIncidence = new InsaneNode[gmshIncidence.length];
	for(int i = 0 ; i < insaneIncidence.length; i++) {
		int gmshIncidenceTag = gmshIncidence[i].getTag();
		int insaneIncidenceTag = getInsaneNodeLabel(gmshIncidenceTag);
		insaneIncidence[i] = insaneModel.getNodeList().get(insaneIncidenceTag - 1);
	}
	insaneElement.setIncidence(insaneIncidence);
}

	private void fillNodesMap(int insaneLabel, int gmshTag) {
		this.nodeLabelInInsane.put(gmshTag, insaneLabel);
	}
	
	private int getInsaneNodeLabel(int gmshLabel) {
		return this.nodeLabelInInsane.get(gmshLabel);
	}

	public String getFilePath() {
		return filePath;
	}

	public InsaneModel getInsaneModel() {
		return insaneModel;
	}
	
	

}
