package nc.unc.ama.discovery_evr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("PMD")
@Configuration
@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
@EnableEurekaServer
public class DiscoveryEvrApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryEvrApplication.class, args);
    }

    @EnableWebSecurity
    /* default */static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
        }
    }
}
