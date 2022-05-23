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

/**
 * Сервисный класс, который отвечает за обработку событий происходящий в приложинии.
 * @author Java Developer
 * @version 1.0
 */
@Service
public class LogsService {

    /** Репоозиторий для доступа к логам. */
    private final LogsRepo repo;

    /** Конструктор Сервиса, в котором инициализируется репозиторий событий. */
    @Autowired
    public LogsService(final LogsRepo repo) {
        this.repo = repo;
    }

    /**
     * Добавляет запись о событии в базу данных.
     *
     * @param entry запись о каком-либо событии произошеднем в приложении
     * @return запись о событии с идентификатором
     */
    @Transactional
    public LogEntry addLog(final LogEntry entry) {
        return this.repo.save(entry);
    }

    public List<LogEntry> findLogsByService(final String service) {
        return this.repo.findLogEntryByServiceOrderByCreated(service);
    }

    /**
     * Возвращает логи за последний час.
     *
     * @return список логов
     */
    @Transactional
    public List<LogEntry> findLastLogs() {
        Instant now = Instant.now();
        Instant yesterday = now.minus(1, ChronoUnit.HOURS);
        return this.repo.getAllLogEntriesByCreatedAfterOrderByCreated(Date.from(yesterday));
    }
}
