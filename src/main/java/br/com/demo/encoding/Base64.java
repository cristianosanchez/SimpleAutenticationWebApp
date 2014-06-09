package br.com.demo.encoding;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.demo.web.HelloServlet;

/**
 * Simple util class for encode and decode BASE64 messages using Apache Commons Codec API.
 * This class provide as result only UTF-8 Strings.
 * @see org.apache.commons.codec.binary.Base64
 * @author cristianosanchez
 */
public class Base64 {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
	
	/**
	 * Encode the given string as Base64
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String msg)  throws UnsupportedEncodingException {
		byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(msg.getBytes());
		String result = new String(encoded, "UTF-8");
		logger.debug("Encoding to Base64 the value ["+msg+"] results ["+result+"]");
		return result;
	}
	
	/**
	 * Decode the given Base64 string
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(String msg) throws UnsupportedEncodingException {
		byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(msg.getBytes());
		String result = new String(decoded, "UTF-8");
		logger.debug("Decoding from Base64 the value ["+msg+"] results ["+result+"]");
		return result;
	}
}
