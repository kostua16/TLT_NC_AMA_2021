package nc.unc.ama.guest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SuppressWarnings("PMD")

@SpringBootApplication
@EnableEurekaClient
public class GuestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestApplication.class, args);
    }

}
