package com.optum.tops.J5427HC1.dao.D54DAO;

import java.util.List;

import com.optum.tops.J5427HC1.models.COBLN_LINE_FLDS;
import com.optum.tops.J5427HC1.models.ReqClaimEntryVO;

public interface IFetchCoblnAmtsDao {

	List<COBLN_LINE_FLDS> getCoblnFlds(ReqClaimEntryVO individual_claim2, String suffix_cd);

}