package main;

import java.util.ArrayList;

import model.Node;
import model.masterdofs.MasterDofs;
import model.masterdofs.XDirectionMasterDofs;
import model.restraints.Restraint;
import model.restraints.XDirectionRestraint;
import model.restraints.XYDirectionRestraint;

public class List {
	private ArrayList<Restraint> restraint = new ArrayList<Restraint>();
	private ArrayList<MasterDofs> masterDofs = new ArrayList<MasterDofs>();
	
	public List(){
		double size = 75.0;
		restraint.add(new XDirectionRestraint(0.0, "true false true"));
		restraint.add(new XYDirectionRestraint(0, size/2, "true true true"));
		restraint.add(new XYDirectionRestraint(size, size, "false false true"));
				
		masterDofs.add(new XDirectionMasterDofs(size, new Node(size, size, 0.0), 1));
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

}
