package nc.unc.ama.common.dto;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


    @Setter
    @Getter
    @EqualsAndHashCode(callSuper = true)
    public class OperationDTO extends OperationCreateDTO {
        private Long idOperation;

        private Boolean status;

        public OperationDTO(Long idOperation, Long operationTypeId, UUID guestId, Double price, Boolean status ) {
            super(operationTypeId, guestId, price);
            this.idOperation = idOperation;
            this.status = status;
        }
    }
