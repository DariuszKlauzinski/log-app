import pl.darek.logapp.domain.LogEvent;
import pl.darek.logapp.domain.exceptions.LogReaderException;
import pl.darek.logapp.domain.ports.EventFileContentReader;

import java.util.List;

public class EventFileContentReaderMock implements EventFileContentReader {
    private List<LogEvent> content;

    public EventFileContentReaderMock(List<LogEvent> content) {
        this.content = content;
    }

    @Override
    public LogEvent getNextEvent(String pathName) throws LogReaderException {
        LogEvent res = null;
        try {
            if (content.get(0) != null) {
                res = content.get(0);
                content.remove(0);
            }
        } catch ( IndexOutOfBoundsException e ) {
            res = null;
        }
        return res;
    }

    @Override
    public boolean hasNextEvent(String pathName) throws LogReaderException {
        boolean res = false;
        try {
            if (content.get(0) != null) {
                res = true;
            }
        } catch ( IndexOutOfBoundsException e ) {
            res = false;
        }
        return res;
    }

}
