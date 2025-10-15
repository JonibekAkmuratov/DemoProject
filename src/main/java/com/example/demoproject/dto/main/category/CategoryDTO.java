package com.example.demoproject.dto.main.category;

import com.example.demoproject.dto.DTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements DTO {

    private Long id;

    private String name;

    private String description;
}
