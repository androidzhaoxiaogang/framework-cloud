package cn.cloud.auth.config;

import cn.cloud.auth.service.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

//@Configuration
//@EnableAuthorizationServer
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

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

//    @Configuration
//    @Order(-20)
//    protected static class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
//        @Autowired
//        private DataSource dataSource;
//
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
//            auth.jdbcAuthentication().dataSource(dataSource)
//                    .withUser("dave").password("secret").roles("USER")
//                    .and()
//                    .withUser("anil").password("password").roles("ADMIN")
//            ;
//        }
//    }

}