package com.diabtrkr.controllers.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.diabtrkr.beans.TokenBean;
import com.diabtrkr.models.User;

@Component
public class TokenUtils {

	private static final Logger logger = LogManager.getLogger();
	private static final String uniqueId = "super secret";
	private static String JWT_ISSUER = "auth";
	public static final long FIFTEEN_MIN_IN_MILLI_SECONDS = 15 * 60 * 1000;

	public String generateToken(User user) {
		Date issuedAt = new Date();
		Date expiresAt = new Date(issuedAt.getTime() + 24 * AppConstants.DAY_IN_MILLI_SECONDS);

		String userId = user.getId();

		Algorithm algorithm = Algorithm.HMAC256(uniqueId);
		String tokenString = JWT.create().withIssuer(JWT_ISSUER).withIssuedAt(issuedAt).withExpiresAt(expiresAt)
				.withClaim("userId", userId).sign(algorithm);

		return tokenString;
	}

	public TokenBean decodeToken(String tokenString) {
		DecodedJWT decode = null;
		TokenBean token = null;
		try {
			decode = JWT.decode(tokenString);
			Algorithm algorithm = Algorithm.HMAC256(uniqueId);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer(JWT_ISSUER).build();
			decode = verifier.verify(tokenString);
			String userId = decode.getClaim("userId") == null ? null : decode.getClaim("userId").asString();

			token = new TokenBean();
			token.setUserId(userId);

		} catch (Exception e) {
			logger.error("decode token failed. token: {}", printException(e));
		}
		return token;
	}

	private TokenBean getCurrentToken() {
		return (TokenBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public String getUserId() {
		return this.getCurrentToken().getUserId();
	}

	public void setManualTokenAuthentication(String userId) {
		TokenBean tokenBean = new TokenBean();
		tokenBean.setUserId(userId);
		SecurityContextHolder.getContext().setAuthentication(new TokenAuthentication(tokenBean));

	}

	private String printException(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		e.printStackTrace(writer);
		return stringWriter.toString();
	}

}
