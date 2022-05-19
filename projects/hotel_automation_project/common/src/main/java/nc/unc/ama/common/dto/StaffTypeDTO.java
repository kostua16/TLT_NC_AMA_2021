package nc.unc.ama.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class StaffTypeDTO extends StaffTypeCreateDTO{

    private Long staffTypeId;

    public StaffTypeDTO(Long staffTypeId, String staffTypeName, String typeDescription) {
        super(staffTypeName, typeDescription);
        this.staffTypeId = staffTypeId;
    }
}
