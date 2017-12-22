package com.optum.tops.mse.rest2.j5427ct2.utilities;

public enum RETURN_MATCH_FOUND_FLAG {
	CPI_RET_MATCH_FND     ("Y"),
	CPI_RET_MATCH_NOT_FND ("N");
    
    private String value;
    
    private RETURN_MATCH_FOUND_FLAG(String value){
            this.value = value;
    }
    
    @Override
    public String toString(){
            return this.value;
    }
}
