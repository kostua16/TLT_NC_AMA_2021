package nc.unc.ama.complaint_handling_service.dto;

import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "LOGGER", path = "/api/logs")
@ConditionalOnProperty(prefix = "app.clients", name = "logsApi")
public interface LogsREST {
    @GetMapping("/")
    List<LogEntryDTO> listLastLogs();

    @PutMapping("/")
    ResponseEntity<LogEntryDTO> addLog(LogEntryDTO input);
}
