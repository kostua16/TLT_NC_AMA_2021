package nc.unc.ama.common.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OperationCreateDTO {

        private Long operationTypeId;
        private UUID guestId;
        private Double price;

}
