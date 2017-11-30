package cn.cloud.auth;

import cn.cloud.auth.service.AuthUserDetailsService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan(basePackages = "cn.cloud.auth.mapper")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }


    @Configuration
    @EnableWebSecurity
    protected static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthUserDetailsService userDetailsService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(new BCryptPasswordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    .csrf().disable();
            // @formatter:on
        }

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers("/swagger-ui.html")
                    .antMatchers("/current")
                    //.antMatchers("/oauth/authorize")
                    .antMatchers("/swagger-resources/**")
                    .antMatchers("/v2/api-docs/**")
                    .antMatchers("/swagger-resources/configuration/security")
                    .antMatchers("/webjars/springfox-swagger-ui/**")
                    .antMatchers("/webjars/**");
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        //private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        private AuthUserDetailsService userDetailsService;

        @Bean
        public JdbcTokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
                    .tokenStore(tokenStore())
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients)
                throws Exception {

            clients.inMemory()
                    //.passwordEncoder(passwordEncoder)
                    .withClient("client")
                    .secret("secret")
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("read", "write")
                    .accessTokenValiditySeconds(3600) // 1 hour
                    .refreshTokenValiditySeconds(2592000) // 30 days
                    .and()
                    .withClient("svca-service")
                    .secret("password")
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("server")
                    .and()
                    .withClient("svcb-service")
                    .secret("password")
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("server")
                    .and()
                    .withClient("browser")
                    .secret("password")
                    .authorizedGrantTypes("refresh_token", "password")
                    .scopes("ui")
                    .and()
                    .withClient("base-service")
                    .secret("password")
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("server")
            ;
        }
    }

}
