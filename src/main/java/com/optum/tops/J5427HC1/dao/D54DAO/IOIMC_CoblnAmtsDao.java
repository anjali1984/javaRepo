package com.optum.tops.J5427HC1.dao.D54DAO;

import java.util.List;

import com.optum.tops.J5427HC1.models.COBLN_2131;

public interface IOIMC_CoblnAmtsDao {

	List<COBLN_2131> getData(String ICN, String Icn_Sufx_Cd, String logId);

}