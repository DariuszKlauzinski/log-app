package pl.darek.logapp.infrastructure.fileContentReader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.darek.logapp.domain.LogEvent;
import pl.darek.logapp.domain.exceptions.LogReaderException;
import pl.darek.logapp.domain.ports.EventFileContentReader;

import java.io.File;
import java.io.IOException;

@Service
public class FileContentReader implements EventFileContentReader {
    private LineIterator iterator = null;
    private static final Logger logger
            = LoggerFactory.getLogger(FileContentReader.class);

    public void initReader(String pathName) throws LogReaderException {
        logger.info("Starting initialization of file content reader for file: {}", pathName);
        try {
            File logFile = new File(pathName);
            this.iterator = FileUtils.lineIterator(logFile, "UTF-8");
            logger.info("Successfully initialized file content reader for file: {}", pathName);
        } catch (IOException e) {
            logger.info("Filed initialization of file content reader for file: {}", pathName);
            logger.info("Exception: {}", e.getMessage());
            throw new LogReaderException("Filed initialization of file content reader for file: " + pathName);
        }
    }

    public LogEvent getNextEvent(String pathName) throws LogReaderException {
        if (iterator == null) {
            logger.debug("Cannot find reader for file: {}, starting initialization.", pathName);
            initReader(pathName);
        }
        logger.debug("Getting new line of content from file: {}", pathName);
        String line = this.iterator.nextLine();
        if (line == null) {
            logger.info("Filed reading of new line of content.");
            throw new LogReaderException("Filed reading of new line of content.");
        }
        LogEventDto logEventDto = null;
        try {
            logEventDto = new ObjectMapper().readValue(line, LogEventDto.class);
        } catch (JsonProcessingException e) {
            logger.info("Filed parsing log content into log object.");
            logger.info("Exception: {}", e.getMessage());
            throw new LogReaderException("Filed parsing log content into log object.");
        }
        return new LogEvent(logEventDto.getId(), logEventDto.getState(), logEventDto.getType(), logEventDto.getHost(), logEventDto.getTimestamp());
    }

    public boolean hasNextEvent(String pathName) throws LogReaderException {
        if (iterator == null) {
            logger.debug("Cannot find reader for file: {}, starting initialization.", pathName);
            initReader(pathName);
        }
        return this.iterator.hasNext();
    }

}
