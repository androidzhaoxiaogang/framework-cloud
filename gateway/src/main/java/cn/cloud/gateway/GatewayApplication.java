package cn.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableSwagger2
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl", "list", "alpha", "schema",
				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
	}

	@Bean
	public CorsFilter corsFilter() {
////		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////		final CorsConfiguration config = new CorsConfiguration();
////		config.setAllowCredentials(true);
////		config.addAllowedOrigin("*");
////		config.addAllowedHeader("*");
////		config.addAllowedMethod("OPTIONS");
////		config.addAllowedMethod("HEAD");
////		config.addAllowedMethod("GET");
////		config.addAllowedMethod("PUT");
////		config.addAllowedMethod("POST");
////		config.addAllowedMethod("DELETE");
////		config.addAllowedMethod("PATCH");
////		source.registerCorsConfiguration("/**", config);
////		return new CorsFilter(source);
//
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/accounts/v2/api-docs", config);
		//source.registerCorsConfiguration("/uaa/**", config);
		//source.registerCorsConfiguration("/oauth/**", config);
		//source.registerCorsConfiguration("/*/api/**", config);
		//source.registerCorsConfiguration("/*/oauth/**", config);
		return new CorsFilter(source);
	}
//
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/uaa/**").allowedOrigins("http://localhost:5000");
//			}
//		};
//	}

//	@Bean
//	public CorsFilter corsFilter() {
//
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("http://localhost:5000");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("GET");
//		config.addAllowedMethod("PUT");
//		source.registerCorsConfiguration("/**", config);
//		return new CorsFilter(source);
//	}

}
