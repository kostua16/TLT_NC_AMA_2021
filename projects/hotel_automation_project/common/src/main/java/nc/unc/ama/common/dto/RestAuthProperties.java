package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.clients.auth")
@Data
@AllArgsConstructor
public class RestAuthProperties {

    public static final String NAME = "api@api.api";
    public static final String DEFAULT_PASS = "api";

    private String password;

    public RestAuthProperties() {
        this.password = RestAuthProperties.DEFAULT_PASS;
    }
}
