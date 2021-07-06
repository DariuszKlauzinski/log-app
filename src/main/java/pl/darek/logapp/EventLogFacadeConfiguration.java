package pl.darek.logapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.darek.logapp.domain.EventLogFacade;
import pl.darek.logapp.domain.ports.EventFileContentReader;
import pl.darek.logapp.domain.ports.EventLogDatabase;

@Configuration
public
class EventLogFacadeConfiguration {

    @Bean
    public EventLogFacade eventLogFacade(EventFileContentReader eventFileContentReader, EventLogDatabase eventLogDatabase) {
        return new EventLogFacade(eventFileContentReader, eventLogDatabase);
    }

}
