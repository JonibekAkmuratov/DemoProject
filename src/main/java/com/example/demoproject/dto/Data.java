package com.example.demoproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data<T> {
    private boolean success;
    private T body;
    private AppErrorDTO error;
    private Long total;

    public Data(T body) {
        this(body, null);
    }

    public Data(T body, Long total) {
        this.body = body;
        this.total = total;
        this.success = true;
    }

    public Data(AppErrorDTO error) {
        this.error = error;
        this.success = false;
    }

    @JsonIgnore
    public boolean isNotSuccess() {
        return !success;
    }
}
