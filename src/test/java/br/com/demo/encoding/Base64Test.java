package br.com.demo.encoding;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.junit.Test;

public class Base64Test {
	
	private final String ORIGINAL_VALUE = "contrato=123&acao=A&nivel=3";
	
	// valores com base no encoding do ORACLE database
	private final String BASE64_VALUE = "Y29udHJhdG89MTIzJmFjYW89QSZuaXZlbD0z";

	@Test
	public void encodeMessageToBase64() {
		try {
			String result = Base64.encode(ORIGINAL_VALUE);
			Assert.assertEquals(BASE64_VALUE, result);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void decodeMessageToBase64() {
		try {
			String result = Base64.decode(BASE64_VALUE);
			Assert.assertEquals(ORIGINAL_VALUE, result);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	} 	
}
