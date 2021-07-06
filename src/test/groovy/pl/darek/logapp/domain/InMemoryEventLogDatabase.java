package pl.darek.logapp.domain;

import pl.darek.logapp.domain.exceptions.LogReaderException;
import pl.darek.logapp.domain.ports.EventLogDatabase;

import java.util.Map;

public class InMemoryEventLogDatabase implements EventLogDatabase {
    private Map<String, LogEventInMemoryEntity> memo;

    public InMemoryEventLogDatabase(Map<String, LogEventInMemoryEntity> memo) {
        this.memo = memo;
    }

    @Override
    public void saveEvent(LogEvent logEvent, long duration, Boolean alert) throws LogReaderException {
        memo.put(logEvent.getId(), LogEventInMemoryEntity.toLogEventInMemoryEntity(logEvent, duration, alert));
    }

    @Override
    public long getNumberOfRecords() {
        return memo.size();
    }

    public LogEventInMemoryEntity getLogEventInMemoryEntityById(String id) {
        return memo.get(id);
    }

}
