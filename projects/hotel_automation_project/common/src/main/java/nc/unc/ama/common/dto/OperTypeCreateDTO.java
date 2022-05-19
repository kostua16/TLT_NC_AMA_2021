package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperTypeCreateDTO {

    private Long staffId;
    private String description;
}
