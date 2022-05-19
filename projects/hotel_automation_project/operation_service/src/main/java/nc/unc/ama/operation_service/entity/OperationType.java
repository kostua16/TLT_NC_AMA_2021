package nc.unc.ama.operation_service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class OperationType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idOperType;
    private Long staffId;
    private String description;

    @Builder
    public OperationType(Long idOperType, Long staffId, String description) {
        this.idOperType = idOperType;
        this.staffId = staffId;
        this.description = description;
    }

    public OperationType() {

    }
}
