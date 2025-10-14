package com.example.demoproject.dto.main.product;

import com.example.demoproject.dto.DTO;
import com.example.demoproject.dto.main.category.CategoryDTO;
import com.example.demoproject.exceptions.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ProductDTO  implements DTO {


    private Long id;

    private String name;

    private String description;

    private Double price;

    private CategoryDTO category;
}
