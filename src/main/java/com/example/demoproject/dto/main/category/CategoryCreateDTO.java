package com.example.demoproject.dto.main.category;

import com.example.demoproject.dto.DTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CategoryCreateDTO  implements DTO {


    private String name;

    private String description;
}
