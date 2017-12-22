package com.optum.tops.mse.rest2.j5427ct2.utilities;

public enum DSM_RETURN_CODE {
	DSM_NO_ERROR("000"),
	DSM_EDIT_ERROR("001"),
	DSM_ENTRY_NOT_FND_INQ("100"),
	DSM_ENTRY_NOT_FND_UPD("900"),
	DSM_CLM_HDR_NOT_FOUND("901"),
	DSM_CLM_LINE_NOT_FOUND("902"),
	DSM_DUPLICATE_CLM_LINE("910"),
	DSM_DUPLICATE_CLM_HDR("920"),
	DSM_APPL_ERROR("998"),
	DSM_DB2_ERROR("999");
    
    private String value;
    
    private DSM_RETURN_CODE(String value){
            this.value = value;
    }
    
    @Override
    public String toString(){
            return this.value;
    }
}
