package nc.unc.ama.complaint_handling_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SuppressWarnings("PMD")

@SpringBootApplication(scanBasePackages = "nc.unc.ama")
@EnableEurekaClient
@EnableFeignClients(basePackages = {"nc.unc.ama"})
@ConfigurationPropertiesScan("nc.unc.ama")
public class ComplaintHandlingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplaintHandlingServiceApplication.class, args);
    }

}
