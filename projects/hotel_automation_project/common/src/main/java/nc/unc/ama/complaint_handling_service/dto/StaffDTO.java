package nc.unc.ama.complaint_handling_service.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class StaffDTO {

    private Long staffId;
    private String staffName;
    private String staffLastName;
    private String staffRating;
    private Double staffSalary;
    private Long staffTypeId;

    public StaffDTO(Long staffId, String staffName, String staffLastName, String staffRating, Double staffSalary, Long staffTypeId) {
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
