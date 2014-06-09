package br.com.pernambucanas.encoding;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64Test {
	
	private final String ORIGINAL_VALUE = "contrato=123&acao=A&nivel=3";
	
	// valores com base no /
	private final String BASE64_VALUE = "Y29udHJhdG89MTIzJmFjYW89QSZuaXZlbD0z";

	// valores com base no site http://www.base64encode.org/
	//private final String BASE64_VALUE = "VG9kYXMgYXMgZmFtw61saWFzIGZlbGl6ZXMgc8OjbyBpZ3VhaXMuIEFzIGluZmVsaXplcyBvIHPDo28gY2FkYSB1bWEgw6Agc3VhIG1hbmVpcmEu";
	
	@Test
	public void encodeHelloToBase64() {
		try {
			byte[] decoded = Base64.decodeBase64(BASE64_VALUE.getBytes());
			String result = new String(decoded, "UTF-8");
			
			Assert.assertEquals(ORIGINAL_VALUE, result);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	} 	
}
