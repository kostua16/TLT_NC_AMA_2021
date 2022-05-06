package nc.unc.ama.complaint_handling_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SuppressWarnings("PMD")

@SpringBootApplication(
    scanBasePackages = {
        "nc.unc.ama.complaint_handling_service",
        "nc.unc.ama.complaint_handling_service.dto"
    }
)
@EnableEurekaClient
@EnableFeignClients
public class ComplaintHandlingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComplaintHandlingServiceApplication.class, args);
    }

}
