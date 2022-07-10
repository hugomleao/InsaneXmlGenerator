package model.masterdofs;

import model.Node;

public class YDirectionMasterDofs extends MasterDofs {

		private double y;
		
		public YDirectionMasterDofs(double y, Node masterNode, int masterDirection) {
			this.y = y;
			this.setMasterNode(masterNode);
			this.setMasterDirection(masterDirection);
		}
		
		@Override
		public void applyMasterDof(Node node) {
			if (node.getY() == this.y) {
				
				
				if (!(node.getX() == this.getMasterNode().getX() && 
						node.getY() == this.getMasterNode().getY())) {
					
					node.setMasterDofs(this.getMasterDofs());
					
				}
				
				
			}
		}
		
		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
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
