package model.insane;

import java.util.HashMap;

public enum AnalysisModel {
	//ANALYSIS_MODEL_NAME("Name in Insane", "DOFs labels")
	PLANE_STRESS_PHASE_FIELD_STAGGERED_SOLVER("PlaneStressPhaseFieldStaggeredSolver", "Dx Dy PF");
	
	private final String insaneName;
	private final String dofLabels;
	public static final HashMap<String, AnalysisModel> BY_INSANE_NAME = new HashMap<>();
	
	static {
        for (AnalysisModel e : values()) {
            BY_INSANE_NAME.put(e.insaneName, e);
        }
    }
	
	private AnalysisModel(String insaneName, String dofLabels) {
	    this.insaneName = insaneName;
	    this.dofLabels = dofLabels;
	  }

	public String getInsaneName() {
		return insaneName;
	}

	public String getDofLabels() {
		return dofLabels;
	}
	
	
}
