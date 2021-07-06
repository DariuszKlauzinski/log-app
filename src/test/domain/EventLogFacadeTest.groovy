import pl.darek.logapp.domain.EventLogFacade
import pl.darek.logapp.domain.LogEvent
import pl.darek.logapp.domain.ports.EventFileContentReader
import pl.darek.logapp.domain.ports.EventLogDatabase
import spock.lang.Specification

class EventLogFacadeTest extends Specification {

    Map<String, LogEventInMemoryEntity> memo = new HashMap<String, LogEventInMemoryEntity>();
    List<LogEvent> content = new ArrayList<>()
    EventFileContentReader eventFileContentReader
    EventLogDatabase eventLogDatabase
    EventLogFacade eventLogFacade

    def setup() {
        memo.clear()
        content.clear()
        eventLogDatabase = new InMemoryEventLogDatabase(memo)
        eventFileContentReader = new EventFileContentReaderMock(content)
    }

    def "facade successfully read, process and save event file with positive alert flag"() {
        given: "a logs with example data"
        content.add(new LogEvent("id", "STARTED", "APPLICATION_LOG","HOST", 1491377495212));
        content.add(new LogEvent("id", "FINISHED", "APPLICATION_LOG","HOST", 1491377495217));
        and: "an eventLogFacade is created"
        eventLogFacade = new EventLogFacade(eventFileContentReader, eventLogDatabase);
        when: "we read and process event file"
        eventLogFacade.readAndProcessEventsFile("path")
        then: "we get saved event with positive alert flag"
        memo.get("id").getAlert()
    }

    def "facade successfully read, process and save event file with negative alert flag"() {
        given: "a logs with example data"
        content.add(new LogEvent("id", "STARTED", "APPLICATION_LOG","HOST", 1491377495212));
        content.add(new LogEvent("id", "FINISHED", "APPLICATION_LOG","HOST", 1491377495213));
        and: "an eventLogFacade is created"
        eventLogFacade = new EventLogFacade(eventFileContentReader, eventLogDatabase);
        when: "we read and process event file"
        eventLogFacade.readAndProcessEventsFile("path")
        then: "we get saved event with negative alert flag"
        !memo.get("id").getAlert()
    }

    def "facade successfully save event into database with positive alert flag"() {
        given: "a log with example data"
        def logEvent = new LogEvent("id", "STARTED", "APPLICATION_LOG","HOST", 1491377495212)
        and: "an eventLogFacade is created"
        eventLogFacade = new EventLogFacade(eventFileContentReader, eventLogDatabase);
        when: "we save event"
        eventLogFacade.saveEvent(logEvent, 5)
        then: "we get saved event with positive alert flag"
        memo.get("id").getAlert()
    }

    def "facade successfully save event into database with negative alert flag"() {
        given: "a log with example data"
        def logEvent = new LogEvent("id", "STARTED", "APPLICATION_LOG","HOST", 1491377495212)
        and: "an eventLogFacade is created"
        eventLogFacade = new EventLogFacade(eventFileContentReader, eventLogDatabase);
        when: "we save event"
        eventLogFacade.saveEvent(logEvent, 2)
        then: "we get saved event with negative alert flag"
        !memo.get("id").getAlert()
    }

}
