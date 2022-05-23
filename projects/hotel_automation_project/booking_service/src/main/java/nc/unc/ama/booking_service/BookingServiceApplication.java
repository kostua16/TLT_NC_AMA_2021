package nc.unc.ama.booking_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SuppressWarnings("PMD")
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"nc.unc.ama"})
@ConfigurationPropertiesScan("nc.unc.ama")
public class BookingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookingServiceApplication.class, args);
    }
}
