package nc.unc.ama.logger.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import nc.unc.ama.logger.dao.LogsRepo;
import nc.unc.ama.logger.entity.LogEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LogServiceTest {
    @InjectMocks
    private LogsService logsService;

    @Mock
    private LogsRepo repo;

    private LogEntry createLogEntry() { // todo: remove
        final LogEntry logEntry = new LogEntry();
        logEntry.setLogId(111);
        logEntry.setCreated(new Date());
        logEntry.setService("some");
        logEntry.setMessage("some");
        return logEntry;
    }

    @Test
    void addLogTest() {
        final LogEntry logEntry = this.createLogEntry();
        when(this.repo.save(any(LogEntry.class))).thenReturn(logEntry);

        final LogEntry response = this.logsService.addLog(logEntry);
        assertThat(response.getLogId()).isEqualTo(logEntry.getLogId());
    }

    @Test
    void findLastLogsTest() {
        final List<LogEntry> logs = Collections.singletonList(createLogEntry());
        when(this.repo.getAllLogEntriesByCreatedAfterOrderByCreated(any(Date.class)))
            .thenReturn(logs);
        final List<LogEntry> response = this.logsService.findLastLogs();
        assertThat(response).isNotNull();
    }

}
