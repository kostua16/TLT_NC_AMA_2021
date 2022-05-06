package nc.unc.ama.common.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class StaffDTO {

    private Long staffId;
    private String staffName;
    private String staffLastName;
    private Integer staffRating;
    private Double staffSalary;
    private Long staffTypeId;

    public StaffDTO(Long staffId, String staffName, String staffLastName, Integer staffRating, Double staffSalary, Long staffTypeId) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffLastName = staffLastName;
        this.staffRating = staffRating;
        this.staffSalary = staffSalary;
        this.staffTypeId = staffTypeId;
    }

    public StaffDTO() {
    }
}
