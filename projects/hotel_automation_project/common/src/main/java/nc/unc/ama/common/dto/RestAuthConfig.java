package nc.unc.ama.common.dto;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RestAuthProperties.class)
public class RestAuthConfig {

    @Bean
    @Autowired
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(final RestAuthProperties properties) {
        return new BasicAuthRequestInterceptor(RestAuthProperties.NAME, properties.getPassword());
    }
}
