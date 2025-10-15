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
public class ErrorDTO {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private String path;
    private String message;
    private Object body;

    public ErrorDTO(String errorPath, String errorMessage) {
        this.path = errorPath;
        this.message = errorMessage;
    }

    public ErrorDTO(String errorPath, String errorMessage, Object errorBody) {
        this(errorPath, errorMessage);
        this.body = errorBody;
    }
}
