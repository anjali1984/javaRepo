CREATE OR REPLACE VIEW VW_CLAIMS (CURSTAT, CUROPER, ICN)
AS
  SELECT cw.curstat,
    cw.curoper,
    cw.icn
  FROM claims_work cw,
    item_flat iflt
  WHERE cw.icn = iflt.cicn
  AND cw.iid   = iflt.iid;