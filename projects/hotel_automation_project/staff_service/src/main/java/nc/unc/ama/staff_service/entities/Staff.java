package nc.unc.ama.staff_service.entities;



import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long staffId;
    private String staffName;
    private String staffLastName;
    private String staffRating;
    private Double staffSalary;
    private Long staffTypeId;

    @Builder
    public Staff(Long staffId, String staffName, String staffLastName, String staffRating, Double staffSalary, Long staffTypeId) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffLastName = staffLastName;
        this.staffRating = staffRating;
        this.staffSalary = staffSalary;
        this.staffTypeId = staffTypeId;
    }

    public Staff() {

    }

}

