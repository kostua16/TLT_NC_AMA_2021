package nc.unc.ama.logger.dao;

import java.util.Date;
import java.util.stream.Stream;
import nc.unc.ama.logger.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepo extends JpaRepository<LogEntry, Integer> {

    Stream<LogEntry> findLogEntryByServiceOrderByCreated(String service);
    Stream<LogEntry> getAllLogEntriesByCreatedAfterOrderByCreated(Date startDate);
}
