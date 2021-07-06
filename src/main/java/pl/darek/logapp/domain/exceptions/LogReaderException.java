package pl.darek.logapp.domain.exceptions;

public class LogReaderException extends Exception {
    public LogReaderException(String errorMessage) {
        super(errorMessage);
    }
}
