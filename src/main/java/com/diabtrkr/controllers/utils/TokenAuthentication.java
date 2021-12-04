package com.diabtrkr.controllers.utils;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.diabtrkr.beans.TokenBean;

public class TokenAuthentication implements Authentication {
	private static final long serialVersionUID = 1L;
	private final TokenBean token;

	public TokenAuthentication(TokenBean token) {
		this.token = token;
	}

	@Override
	public String getName() {
		if (this.token != null)
			return this.token.getUserId();
		return "UNKNOWN_USERID";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// We care about ONLY Principal, others would be null
		return this.token;
	}

	@Override
	public boolean isAuthenticated() {
		// Return always true to make sure Spring Authentication is skipped
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

}
