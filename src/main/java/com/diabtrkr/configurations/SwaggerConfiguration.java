package com.diabtrkr.configurations;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.diabtrkr.controllers.utils.AppConstants;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfiguration Configuration setup
 * 
 * @author Ravi Teja
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	private static final String TOKEN_REFERENCE = "YourToken";
	private static final String TOKEN_TYPE = "header";

	SecurityReference securityReference = SecurityReference.builder().reference(TOKEN_REFERENCE)
			.scopes(new AuthorizationScope[0]).build();

	SecurityContext securityContext = SecurityContext.builder().securityReferences(Arrays.asList(securityReference))
			.build();

	@Bean
	public Docket someApi() {

		return new Docket(DocumentationType.SWAGGER_2).securitySchemes(Arrays.asList(apiKey()))
				.securityContexts(Arrays.asList(securityContext)).apiInfo(metaData()).select()
				.apis(RequestHandlerSelectors.basePackage(AppConstants.APPLICATION_PACKAGE_CONTROLLERS))
				.paths(PathSelectors.any()).build().enable(true)
		// .globalOperationParameters(Arrays.asList(param1)) => NOT REQUIRED, AS WE ARE
		// USING apiKey securitySchemes
		;
	}

	private ApiKey apiKey() {
		return new ApiKey(TOKEN_REFERENCE, AppConstants.AUTHORIZATION_HEADER, TOKEN_TYPE);
	}

	private ApiInfo metaData() {

		return new ApiInfo(AppConstants.APPLICATION_NAME, "Description goes here", "v1.0", "Terms of services",
				new Contact("DiabTrkr Licensing", "https://www.uta.edu", "support@uta.edu"), "license information",
				"https://www.uta.edu/license", Collections.emptyList());
	}

}
