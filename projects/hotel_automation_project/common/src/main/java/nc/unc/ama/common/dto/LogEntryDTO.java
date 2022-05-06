package nc.unc.ama.common.dto;

import java.util.Date;
import lombok.Data;

@Data
public class LogEntryDTO {

    private final Date created;

    private final String service;

    private final String message;
}
