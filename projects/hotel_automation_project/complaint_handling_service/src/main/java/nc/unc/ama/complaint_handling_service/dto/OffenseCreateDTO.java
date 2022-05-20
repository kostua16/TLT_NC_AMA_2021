package nc.unc.ama.complaint_handling_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OffenseCreateDTO {

    private String name;
    private String description;
    private Integer points;
}
