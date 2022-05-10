package nc.unc.ama.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


    @Setter
    @Getter
    @EqualsAndHashCode(callSuper = true)
    public class OperationDTO extends OperationCreateDTO {
        private Long idOperation;

        public OperationDTO(Long idOperation, String operationName, String description, Double price) {
            super(operationName,description,price);
            this.idOperation = idOperation;
        }
    }
