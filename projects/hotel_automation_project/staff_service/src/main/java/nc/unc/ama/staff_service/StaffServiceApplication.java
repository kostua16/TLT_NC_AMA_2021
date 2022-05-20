package nc.unc.ama.staff_service;

import nc.unc.ama.staff_service.config.UsersApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SuppressWarnings("PMD")

@SpringBootApplication(scanBasePackages = "nc.unc.ama")
@EnableFeignClients(basePackages = {"nc.unc.ama"})
@EnableEurekaClient
@EnableAsync
@EnableConfigurationProperties(UsersApiConfig.class)
public class StaffServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaffServiceApplication.class, args);
	}

}
