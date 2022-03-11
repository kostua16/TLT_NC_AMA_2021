package nc.unc.ama.discovery_evr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SuppressWarnings("PMD")
@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
@EnableEurekaServer
public class DiscoveryEvrApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryEvrApplication.class, args);
    }

}
