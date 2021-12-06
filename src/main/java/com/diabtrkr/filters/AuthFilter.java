package com.diabtrkr.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.diabtrkr.beans.TokenBean;
import com.diabtrkr.controllers.utils.AppConstants;
import com.diabtrkr.controllers.utils.AppConstants.StatusCodes;
import com.diabtrkr.controllers.utils.TokenAuthentication;
import com.diabtrkr.controllers.utils.TokenUtils;
import com.diabtrkr.response.dtos.DataResult;
import com.google.gson.Gson;

@Component
@Order(2)
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	TokenUtils jwtUtils;

	private static final Logger logger = LogManager.getLogger();
	private static Gson gson = new Gson();

	private static List<String> exemptedList;

	public AuthFilter() {
		cacheOpenAPIs();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestedURI = httpRequest.getRequestURI();
		String method = httpRequest.getMethod();

		logger.info("{} {}", method, requestedURI);
		boolean authVerified = false;

		TokenBean token = null;

		// Check for OPEN APIs
		authVerified = isOpenAPI(requestedURI);
		if (authVerified) {
			token = new TokenBean();
			token.setUserId("anonymous");
			SecurityContextHolder.getContext().setAuthentication(new TokenAuthentication(token));
			chain.doFilter(request, response);
			return;
		}

		// check for access_token/Bearer token and validate

		String accessToken = request.getHeader(AppConstants.AUTHORIZATION_HEADER);
		if (accessToken != null) {
			accessToken = accessToken.split(" ")[1].trim();
			token = jwtUtils.decodeToken(accessToken);
			authVerified = true;
		}

		if (!authVerified) {
			logger.error("invalid access token: {}", accessToken);
			getAccessDeniedResponse(httpResponse, response.getWriter());
			return;
		}

		SecurityContextHolder.getContext().setAuthentication(new TokenAuthentication(token));

		chain.doFilter(request, response);
	}

	private boolean isOpenAPI(String uri) {
		for (String string : exemptedList) {
			// logger.info("uri: {}, exempted: {}", uri, string);
			if (uri.contains(string))
				return true;
		}
		return false;
	}

	private void cacheOpenAPIs() {
		exemptedList = new ArrayList<String>();
		exemptedList.add(AppConstants.APPLICATION_BASE_PATH + "/swagger");
		exemptedList.add(AppConstants.APPLICATION_BASE_PATH + "/webjars");
		exemptedList.add(AppConstants.APPLICATION_BASE_PATH + "/images");
		exemptedList.add(AppConstants.APPLICATION_BASE_PATH + "/v2");
		exemptedList.add(AppConstants.APPLICATION_BASE_PATH + "/csrf");

		exemptedList.add(AppConstants.APPLICATION_REST_BASE_PATH + "/user/login");
		exemptedList.add(AppConstants.APPLICATION_REST_BASE_PATH + "/user/signup");

	}

	private void getAccessDeniedResponse(HttpServletResponse httpResponse, PrintWriter printWriter) {
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		printWriter
				.println(gson.toJson(new DataResult<>(false, "Unauthorized request", StatusCodes.UNAUTHORIZED.get())));
		printWriter.close();
	}
}