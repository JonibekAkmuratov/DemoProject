package com.example.demoproject.criteria.main;

import com.example.demoproject.criteria.GenericCriteria;
import com.example.demoproject.dto.main.category.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ProductCriteria extends GenericCriteria {

    private String name;

    private Double price;

    private Long categoryId;


}
