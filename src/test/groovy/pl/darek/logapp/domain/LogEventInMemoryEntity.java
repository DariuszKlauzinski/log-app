package pl.darek.logapp.domain;

import lombok.*;

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
class LogEventInMemoryEntity {
    @Id
    private String id;
    private Long duration;
    private String type;
    private String host;
    private Boolean alert;

    public static LogEventInMemoryEntity toLogEventInMemoryEntity(LogEvent logEvent, long duration, Boolean alert) {
        return LogEventInMemoryEntity.builder()
                .id(logEvent.getId())
                .duration(duration)
                .type(logEvent.getType())
                .host(logEvent.getHost())
                .alert(alert)
                .build();
    }

}
