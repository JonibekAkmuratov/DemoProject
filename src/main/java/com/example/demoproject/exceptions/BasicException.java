package com.example.demoproject.exceptions;

import com.example.demoproject.dto.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BasicException extends RuntimeException {
    private int code;
    private String errorMessage;

    public BasicException(int code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public BasicException(int code, Data<?> data) {
        this.code = code;

        if (data != null && data.getError() != null)
            this.errorMessage = data.getError().getErrorMessage();
    }
}
