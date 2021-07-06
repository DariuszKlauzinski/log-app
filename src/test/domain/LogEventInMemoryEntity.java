import lombok.*;
import pl.darek.logapp.domain.LogEvent;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class LogEventInMemoryEntity {
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
