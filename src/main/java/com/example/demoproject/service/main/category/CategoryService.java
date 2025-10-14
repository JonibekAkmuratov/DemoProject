package com.example.demoproject.service.main.category;

import com.example.demoproject.criteria.main.CategoryCriteria;
import com.example.demoproject.dto.main.category.CategoryCreateDTO;
import com.example.demoproject.dto.main.category.CategoryDTO;
import com.example.demoproject.dto.main.category.CategoryUpdateDTO;
import com.example.demoproject.service.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
public interface CategoryService extends GenericCrudService<CategoryDTO, CategoryCreateDTO, CategoryUpdateDTO, CategoryCriteria, Long> {

}
