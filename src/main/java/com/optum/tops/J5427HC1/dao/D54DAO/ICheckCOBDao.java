package com.optum.tops.J5427HC1.dao.D54DAO;

import java.sql.SQLException;

import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;
import com.optum.tops.J5427HC1.models.V5427HC1;

public interface ICheckCOBDao {

	/**
	 * mimics 1100-GET-SUFX-CD section of D5427HC1
	 * Performs below functionality:
	 * 1.Get the suffix code, max icn version nbr, and other   fields from the blke2 table by doing an inner join with  
	 * the adjd_clmsf_blk_e2 and the adjd_clmsf_ln table using  the icn, proc date, and proc tm
	 * 
	 * 2.If the claim is an 835 cob claim, move the fields to their assocoated fields in the return copybook V5427HC1
	 * NEW-COB-LOGC-CD, NEW-835-COB-PROC-IND,   FACL-OR-PROF-CD, AND ALLW-AMT-DTRM-CD    ALSO MOVE 'Y' TO DHC1-COB-CLAIM-INDICATOR.
	 * 
	 * 3.if the claim is not a cob, move 'n' to claim-indicator field and still move the NEW-COB-LOGC-CD, NEW-835-COB-  
	 * PROC-IND, AND THE FACL-OR-PROF-CD FIELDS TO THE RETURN  COPYBOOK, V5427HC1.
	 * @param individual_claim2
	 * @return
	 * 
	 */

	V5427HC1 amICobClaim(ReqClaimEntryVO individual_claim2) ;

}