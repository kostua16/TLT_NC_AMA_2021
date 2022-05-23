package nc.unc.ama.operation_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SuppressWarnings("PMD")
@SpringBootApplication
@EnableFeignClients(basePackages = {"nc.unc.ama"})
@EnableEurekaClient
@ConfigurationPropertiesScan("nc.unc.ama")
public class OperationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperationServiceApplication.class, args);
	}

}
