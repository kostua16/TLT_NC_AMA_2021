package nc.unc.ama.complaint_handling_service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OffenseDTO extends OffenseCreateDTO{

    private Long offenseId;

    public OffenseDTO(Long offenseId, String name, String description, Integer points) {
        super(name, description, points);
        this.offenseId = offenseId;
    }
}
