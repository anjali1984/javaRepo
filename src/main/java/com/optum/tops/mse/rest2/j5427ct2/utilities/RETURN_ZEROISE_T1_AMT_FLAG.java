package com.optum.tops.mse.rest2.j5427ct2.utilities;

public enum RETURN_ZEROISE_T1_AMT_FLAG {
	CPI_RET_ZEROISE_T1 ("Y"),
	CPI_RET_NOT_ZEROISE_T1 ("N");
    
    private String value;
    
    private RETURN_ZEROISE_T1_AMT_FLAG(String value){
            this.value = value;
    }
    
    @Override
    public String toString(){
            return this.value;
    }
}
