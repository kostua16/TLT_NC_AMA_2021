package nc.unc.ama.guest_service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import nc.unc.ama.common.dto.OperationDTO;
import nc.unc.ama.guest_service.entity.Operation;
import nc.unc.ama.guest_service.service.OperationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@Import(ObjectMapper.class)
@WebMvcTest(controllers = {OperationController.class})
public class OperationControllerTest {
    protected static final String GUEST_SERVICE_CONTROLLER_MAPPING =
        "http://localhost:8082/api/operations/";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected OperationServiceImpl operationService;

    private OperationDTO createOperationDTO() {
        return new OperationDTO(111, "name", "description", 111);
    }

    @Test
    void addNewOperation() throws Exception {
        final OperationDTO operationDTO = createOperationDTO();

        doNothing().when(operationService).saveOperation(any(Operation.class));

        this.mockMvc
            .perform(
                post(GUEST_SERVICE_CONTROLLER_MAPPING)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(operationDTO)))
            .andDo(print())
            // todo: create documentation
            .andExpect(status().isOk());
    }
}
