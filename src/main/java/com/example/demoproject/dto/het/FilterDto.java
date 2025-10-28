package com.example.demoproject.dto.het;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {

    private Integer page;
    private Integer size;

}
