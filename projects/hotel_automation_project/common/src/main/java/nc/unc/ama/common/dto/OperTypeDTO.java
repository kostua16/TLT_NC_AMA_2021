package nc.unc.ama.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class OperTypeDTO extends OperTypeCreateDTO {
    private Long idOperType;

    public OperTypeDTO(Long idOperType, Long staffTypeId, String description) {
        super(staffTypeId, description);
        this.idOperType = idOperType;
    }
}
