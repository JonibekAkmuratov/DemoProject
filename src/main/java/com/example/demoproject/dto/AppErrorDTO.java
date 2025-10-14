package com.example.demoproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;



@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AppErrorDTO {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private String errorPath;
    private String errorMessage;
    private Object errorBody;

    public AppErrorDTO(String errorPath, String errorMessage) {
        this.errorPath = errorPath;
        this.errorMessage = errorMessage;
    }

    public AppErrorDTO(String errorPath, String errorMessage, Object errorBody) {
        this(errorPath, errorMessage);
        this.errorBody = errorBody;
    }
}
