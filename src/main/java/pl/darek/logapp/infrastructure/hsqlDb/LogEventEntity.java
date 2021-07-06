package pl.darek.logapp.infrastructure.hsqlDb;

import lombok.*;
import pl.darek.logapp.domain.LogEvent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log")
class LogEventEntity {
    @Id
    private String id;
    private Long duration;
    private String type;
    private String host;
    private Boolean alert;

    public static LogEventEntity toLogEventEntity(LogEvent logEvent, long duration, Boolean alert) {
        return LogEventEntity.builder()
                .id(logEvent.getId())
                .duration(duration)
                .type(logEvent.getType())
                .host(logEvent.getHost())
                .alert(alert)
                .build();
    }

}
