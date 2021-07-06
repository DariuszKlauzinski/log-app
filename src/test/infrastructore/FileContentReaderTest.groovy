import spock.lang.Specification
import pl.darek.logapp.domain.LogEvent
import pl.darek.logapp.domain.ports.EventFileContentReader
import pl.darek.logapp.infrastructure.fileContentReader.FileContentReader

class FileContentReaderTest extends Specification {

    EventFileContentReader eventFileContentReader

    def setup() {
        eventFileContentReader = new FileContentReader()
    }

    def "FileContentReader should check if first event in test file exist"() {
        when: "we check if file content has next event"
        boolean res = eventFileContentReader.hasNextEvent("src/main/resources/logfile.txt")
        then: "we get positive response"
        res
    }

    def "FileContentReader should get first event from test file"() {
        when: "we get first event from file content by get next even method"
        LogEvent res = eventFileContentReader.getNextEvent("src/main/resources/logfile.txt")
        then: "we get event with specific id and timestamp"
        res.getId() == "scsmbstgra" && res.getTimestamp() == 1491377495212
    }

    def "FileContentReader should throw exception if path is wrong"() {
        when: "we try to get first event from file content by get next even method when thr path is wrong"
        LogEvent res = eventFileContentReader.getNextEvent("src/main/resources/test.txt")
        then: "the method throw LogReaderException which extends Exception "
        thrown(Exception)
    }

}
