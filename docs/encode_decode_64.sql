Uma forma de executar encode/decode base 64 no Oracle

-- enable output
set serveroutput on

-- disable define variables or concatenate || chr(38) || for ampersand &
set define off 

declare
    v_str VARCHAR2(1000);
    begin
    --Create encoded value, see http://docs.oracle.com/cd/B28359_01/appdev.111/b28419/u_encode.htm#i1000187
    v_str := utl_encode.text_encode ('contrato=123&acao=A&nivel=3', NULL, UTL_ENCODE.BASE64);
    dbms_output.put_line(v_str);
    
	-- Generate link
    dbms_output.put_line('http://localhost:8080/negociacao/' || v_str);
        
	--Decode the value
    --v_str := utl_encode.text_decode(v_str,'ISO8859-1', UTL_ENCODE.BASE64);
    --dbms_output.put_line(v_str);
end;

