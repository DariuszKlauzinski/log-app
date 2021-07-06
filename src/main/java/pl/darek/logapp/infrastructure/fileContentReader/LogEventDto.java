package pl.darek.logapp.infrastructure.fileContentReader;

import lombok.Data;

@Data
class LogEventDto {
    private String id;
    private String state;
    private String type;
    private String host;
    private Long timestamp;
}
