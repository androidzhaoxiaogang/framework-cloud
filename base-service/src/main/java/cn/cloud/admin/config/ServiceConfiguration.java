package cn.cloud.admin.config;

import cn.cloud.admin.service.security.CustomUserInfoTokenServices;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

//@Configuration
//@EnableResourceServer
//@EnableConfigurationProperties
//@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class ServiceConfiguration extends ResourceServerConfigurerAdapter {
//    @Autowired
//    private ResourceServerProperties sso;
//
//    @Bean
//    @ConfigurationProperties(prefix = "security.oauth2.client")
//    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
//        return new ClientCredentialsResourceDetails();
//    }
//
//    @Bean
//    public RequestInterceptor oauth2FeignRequestInterceptor(){
//        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
//    }
//
//    @Bean
//    public OAuth2RestTemplate clientCredentialsRestTemplate() {
//        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
//    }
//
//    @Bean
//    public ResourceServerTokenServices tokenServices() {
//        return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
//    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext,
                                                            ClientCredentialsResourceDetails resource) {
        return new OAuth2FeignRequestInterceptor(oauth2ClientContext, resource);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated();
    }
}