package pl.darek.logapp.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.darek.logapp.domain.EventLogFacade;
import pl.darek.logapp.domain.ports.EventFileContentReader;
import pl.darek.logapp.domain.ports.EventLogDatabase;

@Configuration
class EventLogFacadeConfiguration {

    @Bean
    public EventLogFacade eventLogFacade(EventFileContentReader eventFileContentReader, EventLogDatabase eventLogDatabase) {
        return new EventLogFacade(eventFileContentReader, eventLogDatabase);
    }

}
