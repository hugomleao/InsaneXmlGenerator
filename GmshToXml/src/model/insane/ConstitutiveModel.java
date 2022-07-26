package model.insane;

import java.util.HashMap;

public enum ConstitutiveModel {
	//CONSTITUTIVE_MODEL("Name in Insane")
	STG_PF_ISOTROPIC_CONST_MODEL("StgPfIsotropicConstModel"),
	STG_PF_LANCIONI_CONST_MODEL("StgPfLancioniConstModel"),
	STG_PF_AMOR_CONST_MODEL("StgPfAmorConstModel"),
	STG_PF_MIEHE_CONST_MODEL("StgPfMieheConstModel");
	
	private final String insaneName;
	public static final HashMap<String, ConstitutiveModel> BY_INSANE_NAME = new HashMap<>();
	
	static {
        for (ConstitutiveModel e : values()) {
            BY_INSANE_NAME.put(e.insaneName, e);
        }
    }
	
	private ConstitutiveModel(String insaneName) {
	    this.insaneName = insaneName;
	  }

	public String getInsaneName() {
		return insaneName;
	}

	
	
}
