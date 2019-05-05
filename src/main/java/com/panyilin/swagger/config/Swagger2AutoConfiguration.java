package com.panyilin.swagger.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(MySwaggerProperties.class)
@ConditionalOnExpression("${swagger2.enable:true}")
public class Swagger2AutoConfiguration {

	@Autowired
	private MySwaggerProperties prop;
	
    @Bean
    public Docket createDocket(){

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title(prop.getTitle())
                        .description(prop.getDesc())
                        .version("1.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(prop.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    
    private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("SpringBoot利用Swagger2构建API文档")
				.description("简单优雅的RESTful风格，http://www.baidu.com")
				.termsOfServiceUrl("http://www.baidu.com")
				.version("1.0")
				.build();
	}
}
