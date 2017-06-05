package application;

import application.filter.AuthenticationFilter;
import application.filter.PerformancePostFilter;
import application.filter.PerformancePreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * User: militer
 * Date: 06.05.2017.
 */

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Bean
    public PerformancePreFilter performancePreFilter() {
        return new PerformancePreFilter();
    }

    @Bean
    public PerformancePostFilter performancePostFilter() {
        return new PerformancePostFilter();
    }
}
