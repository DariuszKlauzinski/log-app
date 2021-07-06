package pl.darek.logapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.darek.logapp.domain.EventLogFacade;
import pl.darek.logapp.domain.exceptions.LogReaderException;

@Component
class CommandLineAppStartupRunner implements CommandLineRunner {
    private EventLogFacade eventLogFacade;

    public CommandLineAppStartupRunner(EventLogFacade eventLogFacade) {
        this.eventLogFacade = eventLogFacade;
    }

    @Override
    public void run(String...args) throws LogReaderException {
        if (args.length == 0) {
            throw new LogReaderException("No path to the log file");
        }
        eventLogFacade.readAndProcessEventsFile(args[0]);
    }

}
