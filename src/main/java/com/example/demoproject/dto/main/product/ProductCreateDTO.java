package com.example.demoproject.dto.main.product;

import com.example.demoproject.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
public class ProductCreateDTO  implements DTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Long categoryId;
}
