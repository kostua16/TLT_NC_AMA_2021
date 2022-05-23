package nc.unc.ama.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class StaffRegistrationDTO extends UserRegistrationDTO{

    private Long staffTypeId;

    public StaffRegistrationDTO(Long staffTypeId, String email, long phone, String firstName, String lastName, String password) {
        super(email, phone, firstName, lastName, password);
        this.staffTypeId = staffTypeId;
    }
}
