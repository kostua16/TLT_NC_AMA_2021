package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OperationCreateDTO {

        private String operationName;
        private String description;
        private Double price;

}
