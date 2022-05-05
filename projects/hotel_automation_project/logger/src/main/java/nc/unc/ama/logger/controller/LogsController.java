package nc.unc.ama.logger.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import nc.unc.ama.complaint_handling_service.dto.LogEntryDTO;
import nc.unc.ama.complaint_handling_service.dto.LogsREST;
import nc.unc.ama.logger.entity.LogEntry;
import nc.unc.ama.logger.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/logs")
public class LogsController implements LogsREST {

    private final LogsService logsService;

    @Autowired
    public LogsController(final LogsService service) {
        this.logsService = service;
    }


    @GetMapping("/")
    @Override
    public List<LogEntryDTO> listLastLogs() {
        return this.logsService.findLastLogs().stream()
            .map(src -> new LogEntryDTO(src.getCreated(), src.getService(), src.getMessage()))
            .collect(Collectors.toList());
    }

    @PutMapping("/")
    @Override
    public ResponseEntity<LogEntryDTO> addLog(@RequestBody final LogEntryDTO input) {
        final LogEntry entry = new LogEntry();
        entry.setCreated(new Date());
        entry.setService(input.getService());
        entry.setMessage(input.getMessage());
        final LogEntry created = this.logsService.addLog(entry);
        return new ResponseEntity<>(
            new LogEntryDTO(created.getCreated(), created.getService(), created.getMessage()),
            HttpStatus.CREATED
        );
    }
}
