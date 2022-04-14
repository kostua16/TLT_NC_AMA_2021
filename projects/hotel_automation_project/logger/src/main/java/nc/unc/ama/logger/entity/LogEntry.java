package nc.unc.ama.logger.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int logId;

    @Column(nullable = false)
    private Date created;

    @Column(length = 10, nullable = false)
    private String service;

    @Column(length = 4000, nullable = false)
    private String message;
}
