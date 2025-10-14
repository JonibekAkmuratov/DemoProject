package com.example.demoproject.dto.main.category;

import com.example.demoproject.dto.DTO;
import com.example.demoproject.exceptions.ErrorCode;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CategoryCreateDTO  implements DTO {


    @NotBlank(message = ErrorCode.NAME_NOT_NULL)
    private String name;

    private String description;
}
