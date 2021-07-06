package pl.darek.logapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class LogEvent {
    private String id;
    private String state;
    private String type;
    private String host;
    private Long timestamp;
}
