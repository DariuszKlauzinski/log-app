package pl.darek.logapp.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.darek.logapp.domain.EventLogFacade;
import pl.darek.logapp.domain.ports.EventFileContentReader;

@Configuration
class EventLogFacadeConfiguration {

    @Bean
    public EventLogFacade eventLogFacade(EventFileContentReader eventFileContentReader) {
        return new EventLogFacade(eventFileContentReader);
    }

}
