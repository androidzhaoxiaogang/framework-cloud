package cn.cloud.idgen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Created by Administrator on 2017/12/12.
 */

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableCircuitBreaker
@EnableOAuth2Client
public class IdGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdGenApplication.class, args);
    }

}
