package nc.unc.ama.complaint_handling_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StaffTypeDTO {

    private Long staffTypeId;
    private String staffTypeName;
    private String typeDescription;

    public StaffTypeDTO(Long staffTypeId, String staffTypeName, String typeDescription) {
        this.staffTypeId = staffTypeId;
        this.staffTypeName = staffTypeName;
        this.typeDescription = typeDescription;
    }

}
