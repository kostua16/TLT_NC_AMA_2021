package nc.unc.ama.user.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import com.google.common.base.Strings;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.api.users")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UsersApiConfig {

    public static final String ADMIN_NAME = "admin@api.api";
    private static final String DEFAULT_PORT = "80";

    private String confirmAddress;

    private String adminPassword;

    @Autowired
    public UsersApiConfig(
        @Value("${server.public-host}") String publicHost,
        @Value("${server.public-port}") String publicPort,
        @Value("${server.port}") String serverPort
    ) {

        this.confirmAddress = String.format(
            "http://%s:%s/",
            this.getDefaultHost(publicHost),
            this.getDefaultPort(publicPort, serverPort)
        );
        this.adminPassword = UsersApiConfig.ADMIN_NAME;
    }

    public String getConfirmationUrl(String token) {
        return "https://discovery-dcoldd.cloud.okteto.net/user/api/users/activate/" + token;
    }

    private String getDefaultPort(String publicPort, String serverPort) {
        String result;
        if (Strings.isNullOrEmpty(publicPort)) {
            if (Strings.isNullOrEmpty(serverPort)) {
                result = UsersApiConfig.DEFAULT_PORT;
            } else {
                result = serverPort;
            }
        } else {
            result = publicPort;
        }
        return result;
    }

    private String getDefaultHost(String publicHost) {
        String result;
        if (Strings.isNullOrEmpty(publicHost)) {
            try {
                result = InetAddress.getLocalHost().getCanonicalHostName();
            } catch (UnknownHostException ignored) {
                result = InetAddress.getLoopbackAddress().getCanonicalHostName();
            }
        } else {
            result = publicHost;
        }
        return result;
    }
}
