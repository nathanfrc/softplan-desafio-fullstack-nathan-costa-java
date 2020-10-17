package com.softplan.processos.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.softplan.processos.api.entity.Usuario;
import com.softplan.processos.api.security.utils.JwtTokenUtil;

//import com.processos.api.security.utils.JwtTokenUtil;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Profile("dev")
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.softplan.processos.api"))
				.paths(PathSelectors.any()).build()
				//.apiInfo(apiInfo()
						.ignoredParameterTypes(Usuario.class)
		                .globalOperationParameters(
		                        Arrays.asList(
		                                new ParameterBuilder()
		                                    .name("Authorization")
		                                    .description("Header para Token JWT")
		                                    .modelRef(new ModelRef("string"))
		                                    .parameterType("header")
		                                    .required(false)
		                                    .build()	)	
						);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Processos")
				.description("Documentação da API de acesso aos endpoints de processos.").version("1.0")
				.build();
	}

	@Bean
	public SecurityConfiguration security() {
		String token;
		try {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername("admin@admin.com");
			token = this.jwtTokenUtil.obterToken(userDetails);
		} catch (Exception e) {
			token = "";
		}

		return new SecurityConfiguration(null, null, null, null, "Bearer " + token, ApiKeyVehicle.HEADER,
				"Authorization", ",");
	}

}
