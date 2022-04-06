package nc.unc.ama.complaint_handling_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SuppressWarnings("PMD")

@SpringBootApplication
@EnableEurekaClient
public class ComplaintHandlingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplaintHandlingServiceApplication.class, args);
    }

}
