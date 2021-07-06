package pl.darek.logapp.infrastructure.hsqlDb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pl.darek.logapp.domain.LogEvent;
import pl.darek.logapp.domain.exceptions.LogReaderException;
import pl.darek.logapp.domain.ports.EventLogDatabase;

@Repository
public class HsqldbLogEventRepository implements EventLogDatabase {
    private HsqldbCrudRepository hsqldbCrudRepository;
    private static final Logger logger
            = LoggerFactory.getLogger(HsqldbLogEventRepository.class);

    public HsqldbLogEventRepository(HsqldbCrudRepository hsqldbCrudRepository) {
        this.hsqldbCrudRepository = hsqldbCrudRepository;
    }

    @Override
    public void saveEvent(LogEvent logEvent, long duration, Boolean alert) throws LogReaderException {
        logger.debug("Starting saving event with id: {}", logEvent.getId());
        try {
            hsqldbCrudRepository.save(LogEventEntity.toLogEventEntity(logEvent, duration, alert));
        } catch (Exception e) {
            logger.info("Cannot save event with id: {} , exception occurred.", logEvent.getId());
            logger.info("Exception: {}", e.getMessage());
            throw new LogReaderException("Cannot save event with id: " + logEvent.getId() + ". " + e.getMessage());
        }
        logger.debug("Successfully saving event with id: {}", logEvent.getId());
    }

    @Override
    public long getNumberOfRecords() {
        return hsqldbCrudRepository.count();
    }
}
