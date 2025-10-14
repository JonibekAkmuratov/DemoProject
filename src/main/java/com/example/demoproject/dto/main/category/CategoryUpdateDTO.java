package com.example.demoproject.dto.main.category;

import com.example.demoproject.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
public class CategoryUpdateDTO  implements DTO {

    private Long id;

    private String name;

    private String description;

}
