package nc.unc.ama.logger.dao;

import java.util.Date;
import java.util.List;
import nc.unc.ama.logger.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepo extends JpaRepository<LogEntry, Integer> {

    List<LogEntry> findLogEntryByServiceOrderByCreated(String service);
    List<LogEntry> getAllLogEntriesByCreatedAfterOrderByCreated(Date startDate);
}
