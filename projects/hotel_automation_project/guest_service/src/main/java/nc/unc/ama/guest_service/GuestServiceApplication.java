package nc.unc.ama.guest_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SuppressWarnings("PMD.ClassWithOnlyPrivateConstructorsShouldBeFinal")
@SpringBootApplication()
@EnableEurekaClient
public class GuestServiceApplication {

	private GuestServiceApplication() {

	}

	public static void main(String[] args) {
		SpringApplication.run(GuestServiceApplication.class, args);
	}

}
