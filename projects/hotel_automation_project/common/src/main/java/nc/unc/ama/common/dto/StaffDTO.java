package nc.unc.ama.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class StaffDTO extends StaffCreateDTO{

    private Long staffId;

    public StaffDTO(Long staffId, String staffName, String staffLastName, Integer staffRating, Double staffSalary, Long staffTypeId) {
        super(staffName, staffLastName, staffRating, staffSalary, staffTypeId);
        this.staffId = staffId;
    }
}
