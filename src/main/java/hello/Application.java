package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
<<<<<<< HEAD
=======
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
>>>>>>> d605ac0feb70a7a5ad87b22d12959644d7d178d9
import org.springframework.context.annotation.Bean;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import utils.UserContextFilter;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.Filter;

@SpringBootApplication
<<<<<<< HEAD
=======
@EnableEurekaClient
@EnableCircuitBreaker
>>>>>>> d605ac0feb70a7a5ad87b22d12959644d7d178d9
//@RefreshScope
//@EnableResourceServer
@EnableScheduling
@EnableHystrixDashboard
public class Application extends SpringBootServletInitializer {

	/*
	@Bean
    public Filter userContextFilter() {
        UserContextFilter userContextFilter = new UserContextFilter();
        return userContextFilter;
    }
    */
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
