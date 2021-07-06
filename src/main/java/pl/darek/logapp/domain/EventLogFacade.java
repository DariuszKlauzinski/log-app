package pl.darek.logapp.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.darek.logapp.domain.exceptions.LogReaderException;
import pl.darek.logapp.domain.ports.EventFileContentReader;
import pl.darek.logapp.domain.ports.EventLogDatabase;

import java.util.HashMap;
import java.util.Map;

public class EventLogFacade {
    private EventFileContentReader eventFileContentReader;
    private EventLogDatabase eventLogDatabase;
    private final Map<String, LogEvent> singleDtoMap;
    private static final Logger logger
            = LoggerFactory.getLogger(EventLogFacade.class);

    public EventLogFacade(EventFileContentReader eventFileContentReader, EventLogDatabase eventLogDatabase) {
        this.eventFileContentReader = eventFileContentReader;
        this.eventLogDatabase = eventLogDatabase;
        this.singleDtoMap = new HashMap<>();
    }

    public void readAndProcessEventsFile(String fileName) {
        logger.info("Start reading and processing log file: {}", fileName);
        try {
            while (eventFileContentReader.hasNextEvent(fileName)) {
                logger.debug("Start reading and processing log event.");
                LogEvent logEvent = eventFileContentReader.getNextEvent(fileName);
                if (singleDtoMap.get(logEvent.getId()) != null) {
                    logger.debug("Event with id: {} , is not an orphan.", logEvent.getId());
                    logger.debug("Start processing event with id: {} , ", logEvent.getId());
                    long duration;
                    LogEvent singleLogEvent = singleDtoMap.get(logEvent.getId());
                    if (("STARTED").equals(logEvent.getState())) {
                        duration = singleLogEvent.getTimestamp() - logEvent.getTimestamp();
                    } else  if (("FINISHED").equals(logEvent.getState())){
                        duration = logEvent.getTimestamp() - singleLogEvent.getTimestamp();
                    } else {
                        logger.debug("Cannot read state of event and calculate duration: {} , ", logEvent.getId());
                        duration = 0;
                    }
                    saveEvent(
                            logEvent.getId() != null ? logEvent : singleLogEvent,
                            duration
                    );
                } else {
                    logger.debug("Event with id: {} , is an orphan and goes to the cache.", logEvent.getId());
                    singleDtoMap.put(logEvent.getId(), logEvent);
                }
            }
            logger.info("Finish of reading and processing log events from file: {} .", fileName);
        } catch (LogReaderException e) {
            logger.info("LogReaderException occurred.");
            logger.info("Exception: {}", e.getMessage());
        }

    }

    public void saveEvent(LogEvent logEvent, long duration) {
        try {
            eventLogDatabase.saveEvent(
                    logEvent,
                    duration,
                    duration > 4
            );
        } catch (LogReaderException e) {
            logger.info("LogReaderException occurred.");
            logger.info("Exception: {}", e.getMessage());
        }
    }

}
