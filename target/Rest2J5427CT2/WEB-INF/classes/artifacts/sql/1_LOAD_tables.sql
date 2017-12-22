-- LOAD STS TYPE
-- LOAD STS TYPE
CREATE TABLE LD_CLM_STS_TYP
  (
    LCS_ID       NUMBER ,
    LCS_GRP_ID   NUMBER   ,
    LCS_TYP_DESC VARCHAR2(4000 CHAR)   ,
    CONSTRAINT PK_LD_CLM_STS_TYP PRIMARY KEY (LCS_ID) ,
    CONSTRAINT UQ_LD_CLM_STS_TYP UNIQUE (LCS_ID, LCS_GRP_ID) 
  );


  
  
  --LD_CLM
CREATE TABLE LD_CLM
  (
    LID         NUMBER   ,
    STS         NUMBER   ,
    FLD_CNT     NUMBER DEFAULT 0   ,
    QUE         VARCHAR2(8 BYTE)   ,
    ICN         VARCHAR2(11 CHAR)   ,
    LDT         VARCHAR2(20 CHAR)   ,
    MSG         VARCHAR2(4000 BYTE)   ,
    ORGID       VARCHAR2(10 CHAR)  ,
    CUR_SFX     VARCHAR2(10 CHAR)   ,
    TOT_SFX     VARCHAR2(10 CHAR)  ,
    PND_CD      VARCHAR2(10 CHAR)   ,
    CLM_ICO_TYP VARCHAR2(1 CHAR)  ,
    LID_ALT     VARCHAR2(200 CHAR)   ,
    RTRY_CNT    NUMBER(2,0) DEFAULT 0   ,
    CONSTRAINT PK_LD_CLM PRIMARY KEY (LID) ,
    CONSTRAINT FK_LD_CLM_STS FOREIGN KEY (STS) REFERENCES PEGA_DATA.LD_CLM_STS_TYP (LCS_ID) 
  );
  comment on column LD_CLM.LID is 'primary key ';
  comment on column LD_CLM.STS is 'status of the load message';
  comment on column LD_CLM.FLD_CNT is 'failure count associated with the suffix message'; 	
  comment on column LD_CLM.QUE is 'type of message - MANUAL or STATUS update';
  comment on column LD_CLM.ICN is 'Claim ICN';
  comment on column LD_CLM.LDT is 'load date of message'; 
  comment on column LD_CLM.MSG is 'actual message';
  comment on column LD_CLM.ORGID is 'ORGID of the claim message' ;
  comment on column LD_CLM.CUR_SFX is 'Suffix number of the claim message'; 
  comment on column LD_CLM.TOT_SFX is 'total suffixes in the claim' ; 
  comment on column LD_CLM.PND_CD is 'pend code for the claim'; 
  comment on column LD_CLM.CLM_ICO_TYP is 'ICO type for the claim' ; 
  comment on column LD_CLM.LID_ALT is 'alternate primary key'; 
  comment on column LD_CLM.RTRY_CNT is 'retry count for the record max 5 times'; 
  
-- LOAD LOCK
CREATE TABLE LD_LCK
  (
    ICN VARCHAR2(11 CHAR)   ,
    CRT_DTM TIMESTAMP (6) DEFAULT systimestamp  ,
	CONSTRAINT PK_LD_LCK PRIMARY KEY(ICN)
  );
  
  
  comment on column LD_LCK.ICN is 'Claim ICN';
  comment on column LD_LCK.CRT_DTM is'lock created time';