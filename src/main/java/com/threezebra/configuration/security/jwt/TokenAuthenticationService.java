package com.threezebra.configuration.security.jwt;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import com.threezebra.exception.ThreeZebraException;
@Service
public class TokenAuthenticationService {

    private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days
    private String secret = "+2lD7X/pCWOm684RN3XhD1OpXMhCLKEoTATN2pkLbd8=";
    private String tokenPrefix = "Bearer";
    private String headerString = "Authorization";
    
	private Map<String, String> secrets = new HashMap<>();
    public void addAuthentication(HttpServletResponse response, String username)
    {
        // We generate a token now.
       /* String JWT = Jwts.builder()
                    .setSubject(username)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    .signWith(SignatureAlgorithm.RS256, secret)
                    .compact();
        response.addHeader(headerString,tokenPrefix + " "+ JWT);*/
    }

    public Authentication getAuthentication(HttpServletRequest request) throws ThreeZebraException
    {
        String authHeaderVal = request.getHeader(headerString);
        HttpsJwks httpsJkws = new HttpsJwks("https://login.microsoftonline.com/DemonstrationOneSimple.onmicrosoft.com/discovery/v2.0/keys");
	    HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJkws);
	    JwtConsumer jwtConsumer = new JwtConsumerBuilder()
	            .setRequireExpirationTime() // the JWT must have an expiration time
	            .setAllowedClockSkewInSeconds(3600) // allow some leeway in validating time based claims to account for clock skew
	            .setRequireSubject() // the JWT must have a subject claim
	            .setExpectedIssuer("https://sts.windows.net/bb66bde2-6356-44da-9fe5-806ac1af46ea/") // whom the JWT needs to have been issued by
	            .setExpectedAudience("https://graph.windows.net") // to whom the JWT is intended for
	            .setVerificationKeyResolver(httpsJwksKeyResolver)
	            .build();
        String username = null;
        if (authHeaderVal.startsWith("Bearer")) {
        	String authHeaderValue = authHeaderVal.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
        	//  Validate the JWT and process it to the Claims
			  JwtClaims jwtClaims;
			try {
				jwtClaims = jwtConsumer.processToClaims(authHeaderValue);
				username = jwtClaims.getSubject();
			} catch (InvalidJwtException  | MalformedClaimException e1) {
			   throw new ThreeZebraException(e1);
			}
        }
        
       if(username != null) // we managed to retrieve a user
            {
                return new AuthenticatedUser(username);
            }
       return null;
    }
}
