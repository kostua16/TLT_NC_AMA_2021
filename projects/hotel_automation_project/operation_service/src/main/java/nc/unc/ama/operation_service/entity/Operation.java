package nc.unc.ama.operation_service.entity;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOperation;

    private Long operationTypeId;

    private UUID guestId;

    private UUID staffId;

    private Double price;

    private Boolean status = false;

    @Builder
    public Operation(Long idOperation, Long operationTypeId, UUID guestId, UUID staffId,
                     Double price) {
        this.idOperation = idOperation;
        this.operationTypeId = operationTypeId;
        this.guestId = guestId;
        this.staffId = staffId;
        this.price = price;
    }
}
