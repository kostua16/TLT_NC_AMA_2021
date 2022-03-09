package nc.unc.ama.booking_service.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;
    private String password;
    private String username;

    public Registration(String password, String username, Long registrationId)
    {
        this.password = password;
        this.username = username;
        this.registrationId = registrationId;
    }

    public Registration()
    {

    }
}
