package com.jiker.bcp.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private Boolean swaggerEnable;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.name}")
    private String name;

    @Value("${swagger.url}")
    private String url;

    @Value("${swagger.email}")
    private String email;

    @Value("${spring.application.name}")
    private String serviceName;

    @Bean
    public Docket createRestApi() {
        Docket docket = null;
        if (swaggerEnable) {
            docket = new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(new ApiInfoBuilder().title(serviceName).description("数据传输接口")
                            .version(version).contact(new Contact(name, url, email)).build())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.jiker"))
                    .paths(PathSelectors.any())
                    .build();
        } else {
            docket = new Docket(DocumentationType.SWAGGER_2).enable(false);
        }
        return docket;
    }

}