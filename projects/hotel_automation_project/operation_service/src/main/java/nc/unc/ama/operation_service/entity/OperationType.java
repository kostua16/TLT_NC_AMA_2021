package nc.unc.ama.operation_service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    private Long idOperType;
    private Long staffTypeId;
    private String description;

    @Builder
    public OperationType(Long idOperType, Long staffTypeId, String description) {
        this.idOperType = idOperType;
        this.staffTypeId = staffTypeId;
        this.description = description;
    }

    public OperationType() {

    }
}
