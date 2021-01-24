package org.tcc.gestao.normas.core.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
		                                              .forCodeGeneration(true)
		                                              .select()
		                                              .apis(RequestHandlerSelectors.any())
		                                              .paths(PathSelectors.regex("/api.*"))
		                                              .build()
		                                              .apiInfo(apiInfo())
		                                              .enable(true);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Gestão de Normas: API REST")
		                           .description("API REST para o serviço: Gestão de Normas")
		                           .build();
	}
}
