package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import model.masterdofs.MasterDofs;
import model.restraints.Restraint;
import reader.Reader;

public class Model {

	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<FemElement> elements = new ArrayList<FemElement>();
	private ArrayList<Region> physicalNames = new ArrayList<Region>();
	private ArrayList<EntitySurface> entitySurfaces = new ArrayList<EntitySurface>();
	private ArrayList<Restraint> restraint = new ArrayList<Restraint>();
	private ArrayList<MasterDofs> masterDofs = new ArrayList<MasterDofs>();


	public void fillModel() throws Exception {
		String path = Reader.readFile();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		this.fillPhysicalNames(bufferedReader);
		this.fillEntitySurfaces(bufferedReader);
		this.fillNodes(bufferedReader);
		this.fillElements(bufferedReader);
	}
	
	private void fillEntitySurfaces(BufferedReader br) throws Exception {
		Reader.findLineThatStartsWith(br, "$Entities");
		String[] split = br.readLine().split(" ");
		int numPoints = Integer.parseInt(split[0]);
		int numCurves = Integer.parseInt(split[1]);
		int numSurfaces = Integer.parseInt(split[2]);
		for(int i = 0; i < numPoints + numCurves; i++) {
			br.readLine();
		}
		for (int i = 0; i < numSurfaces; i++) {
			split = br.readLine().split(" ");
			EntitySurface es = new EntitySurface();
			es.setTag(Integer.parseInt(split[0]));
			int physicalTag = Integer.parseInt(split[8]);
			es.setPhysicalTag(physicalTag);
			this.entitySurfaces.add(es);
		}
	}

	public void fillPhysicalNames(BufferedReader br) throws Exception {
		Reader.findLineThatStartsWith(br, "$PhysicalNames");
		int numPhysicalNames = Integer.parseInt(br.readLine());
		for (int i = 0; i<numPhysicalNames; i++) {
			String[] split = br.readLine().split(" ");
			String name = split[2];
			name = name.replace("\"", "");
			Region region = new Region(name, Integer.parseInt(split[1]));
			this.physicalNames.add(region);
		}
	}

	public void fillNodes(BufferedReader br) throws Exception {
		Reader.findLineThatStartsWith(br, "$Nodes");
		String line = br.readLine();
		String [] split = line.split(" ");
		int numNodes = Integer.parseInt(split[1]);
		do {
			line = br.readLine();
			split = line.split(" ");
			int nodesToRead = Integer.parseInt(split[3]);
			String [] nodesLabel = new String[nodesToRead];
			for (int i = 0; i<nodesToRead; i++) {
				nodesLabel[i] = br.readLine();
			}
			for (int i = 0; i<nodesToRead; i++) {
				String[] coords = br.readLine().split(" ");
				double x = Double.parseDouble(coords[0]);
				double y = Double.parseDouble(coords[1]);
				double z = Double.parseDouble(coords[2]);
				Node node = new Node(nodesLabel[i], x, y, z);
				this.nodes.add(node);
			}
		} while (this.nodes.size() < numNodes);
	}

	public void fillElements(BufferedReader br) throws Exception {
		Reader.findLineThatStartsWith(br, "$Elements");
		String line = br.readLine();
		String [] split = line.split(" ");
		int numBlocks = Integer.parseInt(split[0]);
		for (int elmBlock = 0; elmBlock < numBlocks; elmBlock++) {
			String[] splitBlock = br.readLine().split(" ");
			int entityTag = Integer.parseInt(splitBlock[1]);
			int elmsInBlock = Integer.parseInt(splitBlock[3]);
			for (int elmNumb = 0; elmNumb < elmsInBlock; elmNumb++) {
				splitBlock = br.readLine().split(" ");
				FemElement element = new FemElement();
				EntitySurface es = this.findEntitySurface(entityTag);
				Region region = this.findRegion(es.getPhysicalTag());
				element.setRegion(region);
				int elmId = Integer.parseInt(splitBlock[0]);
				element.setId(elmId);
				for (int i = 1; i < splitBlock.length; i++) {
					Node node = this.findNode(splitBlock[i]);
					element.getNodes().add(node);
				}
				this.elements.add(element);
			}
		}
	}

	private Node findNode(String id) {
		Iterator<Node> nodesIterator = this.nodes.iterator();
		while (nodesIterator.hasNext()) {
			Node node = nodesIterator.next();
			if (node.getId().equals(id)) {
				return node;
			}
		}
		return null;
	}
	
	public Node findNode(double x, double y, double z) {
		Iterator<Node> nodesIterator = this.nodes.iterator();
		while (nodesIterator.hasNext()) {
			Node node = nodesIterator.next();
			if (node.getX() == x && node.getY() == y && node.getZ() == z) {
				return node;
			}
		}
		return null;
	}
	
	private Region findRegion(int number) {
		Iterator<Region> regionIterator = this.physicalNames.iterator();
		while (regionIterator.hasNext()) {
			Region region = regionIterator.next();
			if (region.getTag() == number) {
				return region;
			}
		}
		return null;
	}
	
	private EntitySurface findEntitySurface(int tag) {
		Iterator<EntitySurface> entitySurfaceIterator = this.entitySurfaces.iterator();
		while (entitySurfaceIterator.hasNext()) {
			EntitySurface entity = entitySurfaceIterator.next();
			if (entity.getTag() == tag) {
				return entity;
			}
		}
		return null;
	}
	
	public void putRestraints() {
		Iterator<Restraint> i = this.restraint.iterator();
		while (i.hasNext()) {
			Restraint restraint = i.next();
			Iterator<Node> j = this.nodes.iterator();
			while (j.hasNext()) {
				Node node = j.next();
				restraint.applyRestraint(node);
			}
		}
	}
	
	public void putMasterDofs() {
		Iterator<MasterDofs> i = this.masterDofs.iterator();
		while (i.hasNext()) {
			MasterDofs masterdofs = i.next();
			Node master = masterdofs.getMasterNode();
			String masterId = this.findNode(master.getX(), master.getY(), master.getZ()).getId();
			master.setId(masterId);
			Iterator<Node> j = this.nodes.iterator();
			while (j.hasNext()) {
				Node node = j.next();
				masterdofs.applyMasterDof(node);
			}
		}
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<FemElement> getElements() {
		return elements;
	}

	public void setElements(ArrayList<FemElement> elements) {
		this.elements = elements;
	}
	
	public ArrayList<Region> getPhysicalNames() {
		return physicalNames;
	}

	public void setPhysicalNames(ArrayList<Region> physicalNames) {
		this.physicalNames = physicalNames;
	}

	public ArrayList<Restraint> getRestraint() {
		return restraint;
	}

	public void setRestraint(ArrayList<Restraint> restraint) {
		this.restraint = restraint;
	}

	public ArrayList<MasterDofs> getMasterDofs() {
		return masterDofs;
	}

	public void setMasterDofs(ArrayList<MasterDofs> masterDofs) {
		this.masterDofs = masterDofs;
	}
	
	public ArrayList<EntitySurface> getEntitySurfaces() {
		return entitySurfaces;
	}

	public void setEntitySurfaces(ArrayList<EntitySurface> entitySurfaces) {
		this.entitySurfaces = entitySurfaces;
	}

}
