package nc.unc.ama.logger.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import nc.unc.ama.common.dto.LogEntryDTO;
import nc.unc.ama.logger.controller.LogsController;
import nc.unc.ama.logger.entity.LogEntry;
import nc.unc.ama.logger.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@Import(ObjectMapper.class)
@WebMvcTest(controllers = {LogsController.class})
public class LogControllerTest {

    protected static final String LOGGER_CONTROLLER_MAPPING =
        "http://localhost:4444/api/logs";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected LogsService logsService;

    private LogEntryDTO createLogEntryDTO() {
        return new LogEntryDTO(new Date(), "some", "some");
    }

    private LogEntry createLogEntry() { // todo: remove
        final LogEntry logEntry = new LogEntry();
        logEntry.setLogId(111);
        logEntry.setCreated(new Date());
        logEntry.setService("some");
        logEntry.setMessage("some");
        return logEntry;
    }

    //    @Test
    void addLogTest() throws Exception {
        final LogEntryDTO input = this.createLogEntryDTO();
        final LogEntry logEntry = this.createLogEntry();

        when(this.logsService.addLog(any(LogEntry.class))).thenReturn(logEntry);

        this.mockMvc
            .perform(
                put(LOGGER_CONTROLLER_MAPPING)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(input)))
            .andDo(print())
            .andExpect(status().isCreated());
    }
}
