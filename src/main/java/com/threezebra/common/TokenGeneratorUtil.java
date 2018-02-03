package com.threezebra.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.naming.ServiceUnavailableException;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientAssertion;
import com.microsoft.aad.adal4j.ClientCredential;

public class TokenGeneratorUtil {

	private final static String AUTHORITY = "https://login.microsoftonline.com/DemonstrationOneSimple.onmicrosoft.com/oauth2/token";
	private final static String CLIENT_ID = "c0e45e04-99e1-42fa-86a9-442cd894832b";
	private final static String CLIENT_SECRET = "+2lD7X/pCWOm684RN3XhD1OpXMhCLKEoTATN2pkLbd8=";

	public static String getAccessTokenForResource(String resource, String userAccessToken) throws Exception {
		if (userAccessToken.startsWith("Bearer")) {
			 userAccessToken = userAccessToken.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
		 }
		AuthenticationContext context = null;
		AuthenticationResult result = null;
		ExecutorService service = null;
		ClientCredential credential = new ClientCredential(CLIENT_ID, CLIENT_SECRET);
		ClientAssertion clientAssertion = new ClientAssertion(userAccessToken);
		
		try {
			service = Executors.newFixedThreadPool(1);
			context = new AuthenticationContext(AUTHORITY, false, service);
			Future<AuthenticationResult> future = context.acquireToken(resource,clientAssertion, credential, null);
			result = future.get();
		} finally {
			service.shutdown();
		}
		if (result == null) {
			throw new ServiceUnavailableException("authentication result was null");
		}
		return result.getAccessToken();
	}
	
	

}
