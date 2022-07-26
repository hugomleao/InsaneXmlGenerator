package model.insane;

public class NodeLoad {
	private String load;
	private InsaneNode node;
	
	
	public NodeLoad(String load, InsaneNode node) {
		this.load = load;
		this.node = node;
	}


	public String getLoad() {
		return load;
	}


	public void setLoad(String load) {
		this.load = load;
	}


	public InsaneNode getNode() {
		return node;
	}


	public void setNode(InsaneNode node) {
		this.node = node;
	}
	
	
	

}
