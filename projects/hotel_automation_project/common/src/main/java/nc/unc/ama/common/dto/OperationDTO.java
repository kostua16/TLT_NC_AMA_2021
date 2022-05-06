package nc.unc.ama.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


    @Setter
    @Getter
    @NoArgsConstructor
    public class OperationDTO {
        private int idOperation;
        private String operationName;
        private String description;
        private int price;

        public OperationDTO(int idOperation, String operationName, String description, int price) {
            this.idOperation = idOperation;
            this.operationName = operationName;
            this.description = description;
            this.price = price;
        }
    }
