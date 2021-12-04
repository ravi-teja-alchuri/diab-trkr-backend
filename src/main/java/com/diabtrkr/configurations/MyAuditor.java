package com.diabtrkr.configurations;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.diabtrkr.beans.TokenBean;
import com.diabtrkr.controllers.utils.TokenAuthentication;

@Component
public class MyAuditor implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		TokenAuthentication authentication = (TokenAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		TokenBean token = (TokenBean) authentication.getPrincipal();
		return Optional.of(token.getUserId());
	}

}
