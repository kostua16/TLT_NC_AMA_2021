package nc.unc.ama.user;

import nc.unc.ama.user.config.UsersApiConfig;
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
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
