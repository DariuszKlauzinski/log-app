package pl.darek.logapp;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.darek.logapp.domain.ports.EventLogDatabase;

@SpringBootTest(args = "src/main/resources/logfile.txt")
class ApplicationTests {

    @Autowired
    EventLogDatabase eventLogDatabase;

    @Test
    void contextLoadsReadAndProcessEventsFileAndCheckResult() {
        long res = eventLogDatabase.getNumberOfRecords();
        Assert.assertEquals(res, 3);
    }

}
