package com.example.demoproject.dto.het;


import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterReport2Dto extends FilterDto {
    protected String name;
    protected Integer age;
}
