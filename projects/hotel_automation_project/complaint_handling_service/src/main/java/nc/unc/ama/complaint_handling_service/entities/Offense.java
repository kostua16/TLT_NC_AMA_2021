package nc.unc.ama.complaint_handling_service.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Offense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offenseId;
    private String name;
    private  String description;
    private Integer points;

    @Builder
    public Offense(Long offenseId, String name, String description, Integer points) {
        this.offenseId = offenseId;
        this.name = name;
        this.description = description;
        this.points = points;
    }
}
