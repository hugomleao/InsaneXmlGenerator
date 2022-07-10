package model.masterdofs;

import model.Node;

public class XDirectionMasterDofs extends MasterDofs {

		private double x;
		
		public XDirectionMasterDofs(double x, Node masterNode, int masterDirection) {
			this.x = x;
			this.setMasterNode(masterNode);
			this.setMasterDirection(masterDirection);
		}
		
		@Override
		public void applyMasterDof(Node node) {
			if (node.getX() == this.x) {
				
				
				if (!(node.getX() == this.getMasterNode().getX() && 
						node.getY() == this.getMasterNode().getY())) {
					
					node.setMasterDofs(this.getMasterDofs());
					
				}
				
				
			}
		}
		
		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		@Override
		public String getMasterDofs() {
			String resp = "";
			for (int i = 0; i<3; i++) {
				if (i == this.getMasterDirection() - 1) {
					resp += this.getMasterNode().getId() + " ";
				} else {
					resp += "false ";
				}
			}
						return resp.trim();
		}

	
		
}
