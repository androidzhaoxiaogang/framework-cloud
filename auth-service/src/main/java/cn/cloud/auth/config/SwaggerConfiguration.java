package cn.cloud.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Administrator on 2017/10/6.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Value("${uaa.clientId}")
    String clientId;

    @Value("${uaa.clientSecret}")
    String clientSecret;

    @Value("${uaa.url}")
    String oAuthServerUri;

    @Bean
    public Docket authApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.groupName("doc-api")
                .apiInfo(apiInfo())
                .select()
                //.paths(authPaths())
                .apis(RequestHandlerSelectors.basePackage("cn.cloud.auth.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(oauth()))
                .securityContexts(newArrayList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springfox auth API")
                .description("api doc.")
                .termsOfServiceUrl("http://springfox.io")
                .contact(new Contact("zhao xiaogang", "https://github.com/androidzhaoxiaogang" , "zxgangandy@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

    @Bean
    SecurityScheme oauth() {
        return new OAuthBuilder()
                .name("oauth2")
                .scopes(scopes())
                .grantTypes(grantTypes())
                .build();
    }

    List<AuthorizationScope> scopes() {
        List<AuthorizationScope> list = new ArrayList();
        list.add(new AuthorizationScope("read","Grants read access"));
        list.add(new AuthorizationScope("write","Grants write access"));

        return list;
    }

    List<GrantType> grantTypes() {
        List<GrantType> grantTypes = new ArrayList<>();
        TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(oAuthServerUri +
                "/oauth/authorize", clientId, clientSecret);
        TokenEndpoint tokenEndpoint = new TokenEndpoint(oAuthServerUri + "/oauth/token", "access_token");
        grantTypes.add(new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint));
        return grantTypes;
    }

    @Bean
    SecurityContext securityContext() {
        AuthorizationScope[] scopes = new AuthorizationScope[2];
        scopes[0] = new AuthorizationScope( "read", "Grants read access" );
        scopes[1] = new AuthorizationScope( "write", "Grants write access" );

        SecurityReference securityReference = SecurityReference
                .builder()
                .reference("oauth2")
                .scopes(scopes)
                .build();


        return SecurityContext
                .builder()
                .securityReferences(newArrayList(securityReference))
                .forPaths(PathSelectors.any())
                .build();
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(clientId, clientSecret, "realm", clientId,
                "apiKey", ApiKeyVehicle.HEADER, "api_key", ",");
    }
}
