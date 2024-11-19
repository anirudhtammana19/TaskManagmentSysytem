package com.TaskManagement;



import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;

public class generatesecretkeyTest {
	
	@Test
	public void generateSecretKey() {
		SecretKey secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
		String key = DatatypeConverter.printHexBinary(secretKey.getEncoded());
		System.out.println(key);
	}

}
