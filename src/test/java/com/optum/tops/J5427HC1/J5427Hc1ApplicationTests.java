package com.optum.tops.J5427HC1;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.optum.tops.J5427HC1.models.V5427HC1;
import com.optum.tops.J5427HC1.services.RequestProcessor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class J5427Hc1ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	
	@Test
	public void WriteOff2200Test()
	{
		RequestProcessor reqPrcr=new RequestProcessor();
		V5427HC1 currentClaim =new V5427HC1();
		currentClaim.setHC1_COB_INST_OR_PROF("I");
		currentClaim.setHC1_COB_COB_835_PROC_IND("M");
		currentClaim.setHC1_COB_NBR_LINES(1);
		currentClaim.setHC1_COB_835_COB_PRIM_IMPAC(BigDecimal.ZERO);
		System.out.println("ten "+currentClaim.getHC1_COB_835_COB_PRIM_IMPAC());
		
		BigDecimal compute=currentClaim.getHC1_COB_835_COB_PRIM_IMPAC().add(BigDecimal.TEN).subtract(BigDecimal.TEN);
		System.out.println("compute "+compute);

		/*int cobDxCnt=0;
		currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).setHC1_COB_LN_835_COB_PRIM_IMPAC(BigDecimal.TEN);
		System.out.println("ten "+currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).getHC1_COB_LN_835_COB_PRIM_IMPAC());
		currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).setHC1_COB_LN_EOB_MEDC_PAID_AMT(BigDecimal.ONE);
		currentClaim.getHC1_COB_LNE_DATA_AREA().get(cobDxCnt).setHC1_COB_LN_EOB_OI_PAID_AMT(BigDecimal.ONE);*/
		//currentClaim.setHC1_COB_OI_PAID_AMT(hC1_COB_OI_PAID_AMT);
		//reqPrcr.WriteOff2200(currentClaim);
	}
	

}
