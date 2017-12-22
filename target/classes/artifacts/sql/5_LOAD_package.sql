create or replace
PACKAGE comet_mcfmct AS 
  function incrmnt_numvl (inp_vl number)  
    return number;
end comet_mcfmct; 

create or replace
PACKAGE BODY comet_mcfmct as
  function incrmnt_numvl (inp_vl number) 
  return number is ret_vl number;
  begin
    ret_vl := inp_vl + 1;
    return (ret_vl);
  end;
end comet_mcfmct;