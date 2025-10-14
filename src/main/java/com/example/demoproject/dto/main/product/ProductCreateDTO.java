package com.example.demoproject.dto.main.product;

import com.example.demoproject.dto.DTO;
import com.example.demoproject.exceptions.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
public class ProductCreateDTO  implements DTO {

    @NotBlank(message = ErrorCode.NAME_NOT_NULL)
    private String name;

    private String description;

    @NotBlank(message = ErrorCode.PRICE_NOT_NULL)
    private Double price;

    @NotBlank(message = ErrorCode.CATEGORY_ID_NOT_NULL)
    private Long categoryId;
}
