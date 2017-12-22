CREATE OR REPLACE EDITIONABLE TRIGGER ITEMFLATSTAGE_ITEMID_TRIG BEFORE
  INSERT ON ITEM_FLAT_STAGE FOR EACH ROW BEGIN
  SELECT ITEMFLATSTAGE_ITEMID_SEQ.NEXTVAL INTO :new.ITEMID FROM dual;
END;

ALTER TRIGGER ITEMFLATSTAGE_ITEMID_TRIG ENABLE;