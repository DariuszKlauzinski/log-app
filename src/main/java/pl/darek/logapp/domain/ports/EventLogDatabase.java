package pl.darek.logapp.domain.ports;

import pl.darek.logapp.domain.LogEvent;
import pl.darek.logapp.domain.exceptions.LogReaderException;

public interface EventLogDatabase {

    void saveEvent(LogEvent logEvent, long duration, Boolean alert) throws LogReaderException;

    long getNumberOfRecords();

}
