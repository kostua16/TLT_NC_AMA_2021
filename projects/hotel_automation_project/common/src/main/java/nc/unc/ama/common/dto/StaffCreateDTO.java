package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaffCreateDTO {
    private String staffName;
    private String staffLastName;
    private Integer staffRating;
    private Double staffSalary;
    private Long staffTypeId;
}
