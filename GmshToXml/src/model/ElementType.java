package model;

import java.util.HashMap;

public enum ElementType {
	//NAME_OF_ENUM(gmeshType, numberOfNodes, InsaneElementName, InsaneIntegrationOrder)
	LINE_2_NODES(1, 2, "", ""),
	TRIANGLE_3_NODES(2, 3, "ParametricElement.Triangular.T3", "1 0 0"),
	QUADRANGLE_4_NODES(3, 4, "ParametricElement.Quadrilateral.Q4", "2 2 0"),
	POINT_1_NODE(15, 1, "", "");
	
	private final int gmshType;
	private final int numberOfNodes;
	private final String insaneType;
	private final String integrationOrder;
	public static final HashMap<Integer, ElementType> BY_TAG = new HashMap<>();
	
	static {
        for (ElementType e : values()) {
            BY_TAG.put(e.gmshType, e);
        }
    }
	
	private ElementType(int value, int nNodes, String insaneType, String integrationOrder) {
	    this.gmshType = value;
	    this.insaneType = insaneType;
	    this.numberOfNodes = nNodes;
	    this.integrationOrder = integrationOrder;
	  }
	
	public String getInsaneElement() {
		return this.insaneType;
	}
	
	public int getGmshElementType() {
		return this.gmshType;
	}
	
	public int getNumberOfNodes() {
		return this.numberOfNodes;
	}
	
	public String getIntegrationOrder() {
		return this.integrationOrder;
	}
	
	
}
