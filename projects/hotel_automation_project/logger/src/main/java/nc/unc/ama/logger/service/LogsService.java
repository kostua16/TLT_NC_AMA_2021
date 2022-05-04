package nc.unc.ama.logger.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import nc.unc.ama.logger.dao.LogsRepo;
import nc.unc.ama.logger.entity.LogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogsService {

    private final LogsRepo repo;

    @Autowired
    public LogsService(final LogsRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public LogEntry addLog(final LogEntry entry) {
        return this.repo.save(entry);
    }

    public List<LogEntry> findLogsByService(final String service) {
        return this.repo.findLogEntryByServiceOrderByCreated(service);
    }

    @Transactional
    public List<LogEntry> findLastLogs() {
        Instant now = Instant.now();
        Instant yesterday = now.minus(1, ChronoUnit.HOURS);
        return this.repo.getAllLogEntriesByCreatedAfterOrderByCreated(Date.from(yesterday));
    }
}
