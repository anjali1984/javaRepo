ALTER TABLE ITEM_FLAT
  MODIFY (CV2_SFX_AMT NUMBER(10,2),
          CAMT NUMBER(10,2),
          CNSL_ERR   VARCHAR2(200) 
         );
ALTER TABLE ITEM_FLAT
  MODIFY (
          IID   VARCHAR2(20) 
         );
ALTER TABLE ITEM_FLAT_STAGE
  MODIFY (
          IID   VARCHAR2(20) 
         );



       
ALTER TABLE ITEM_FLAT DROP column CPLE ;             
ALTER TABLE ITEM_FLAT DROP column CUROPER     ;       
ALTER TABLE ITEM_FLAT DROP column CURSTAT   ;        
ALTER TABLE ITEM_FLAT DROP column CURPT   ;          
ALTER TABLE ITEM_FLAT DROP column OPEN_OR_RESOLVED  ;
ALTER TABLE ITEM_FLAT DROP column TOTPRIOR    ;      
ALTER TABLE ITEM_FLAT DROP column CPRV ;             
ALTER TABLE ITEM_FLAT DROP column CPWB    ;          
ALTER TABLE ITEM_FLAT DROP column RESOL_DATE   ;    
ALTER TABLE ITEM_FLAT DROP column I_IID ;
ALTER TABLE ITEM_FLAT_STAGE DROP column I_IID ;
