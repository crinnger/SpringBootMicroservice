package br.com.crinnger.SpringBootMicroservice.swagger;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import br.gov.am.prodam.apirenavamconector.logging.LogApiInterceptor;
//import br.gov.am.prodam.apirenavamconector.security.AuthApiInterceptor;
//import br.gov.am.prodam.apirenavamconector.ticketing.TicketInterceptor;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

//	@Resource
//	private LogApiInterceptor logApiInterceptor;
//
//	@Resource
//	private AuthApiInterceptor authApiInterceptor;
//
//	@Resource
//	private TicketInterceptor ticketInterceptor;
	
	@Value("${app.versao}")
	private String versao;

	public static final Contact DEFAULT_CONTACT = new Contact("PRODAM", "http://www.prodam.am.gov.br", "");

	private ApiInfo getApiInfo() { return new ApiInfoBuilder().title("API BEER")
			.description("Projeto para Treinamento")
			.version(versao).contact(DEFAULT_CONTACT).build(); }

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("text/csv"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo())
				.securitySchemes(Collections.singletonList(new ApiKey("JWT", "Authorization", "header")))
				.produces(DEFAULT_PRODUCES_AND_CONSUMES).consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.securityContexts(Collections.singletonList(SecurityContext.builder()
						.securityReferences(Collections.singletonList(
								SecurityReference.builder().reference("JWT").scopes(new AuthorizationScope[0]).build()))
						.build()))
				.tags(new Tag("Beer", "", 0),
						new Tag("Customer", "", 1) 
				     )
				.select().apis(RequestHandlerSelectors.basePackage("br.com.crinnger.SpringBootMicroservice.web.controller"))
				.build();
	}
//
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
////		registry.addInterceptor(logApiInterceptor).addPathPatterns("/api/**");
////		registry.addInterceptor(authApiInterceptor).addPathPatterns("/api/**");
////		registry.addInterceptor(ticketInterceptor).addPathPatterns("/api/**");
//	}

}
