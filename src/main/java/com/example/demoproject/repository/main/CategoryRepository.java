package com.example.demoproject.repository.main;

import com.example.demoproject.criteria.main.CategoryCriteria;
import com.example.demoproject.dto.main.category.CategoryCreateDTO;
import com.example.demoproject.dto.main.category.CategoryDTO;
import com.example.demoproject.dto.main.category.CategoryUpdateDTO;
import com.example.demoproject.entity.main.Category;
import com.example.demoproject.repository.GenericCrudRepository;
import com.example.demoproject.service.GenericCrudService;
import org.springframework.stereotype.Repository;

public interface CategoryRepository extends GenericCrudRepository<Category, Long, CategoryCriteria> {

}
