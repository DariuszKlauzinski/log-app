package pl.darek.logapp.domain.ports;

import pl.darek.logapp.domain.LogEvent;
import pl.darek.logapp.domain.exceptions.LogReaderException;

public interface EventFileContentReader {

    LogEvent getNextEvent(String pathName) throws LogReaderException;

    boolean hasNextEvent(String pathName) throws LogReaderException;

}
