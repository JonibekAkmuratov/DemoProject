package com.example.demoproject.dto.main.product;

import com.example.demoproject.dto.DTO;
import com.example.demoproject.exceptions.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ProductUpdateDTO  implements DTO {

    @NotNull(message = ErrorCode.ID_NOT_NULL)
    private Long id;

    @NotBlank(message = ErrorCode.NAME_NOT_NULL)
    private String name;

    private String description;

    @NotNull(message = ErrorCode.PRICE_NOT_NULL)
    private Double price;

    @NotNull(message = ErrorCode.CATEGORY_ID_NOT_NULL)
    private Long categoryId;

}
