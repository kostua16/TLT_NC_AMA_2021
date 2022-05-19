package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Deprecated
public class StaffTypeCreateDTO {
    private String staffTypeName;
    private String typeDescription;
}
