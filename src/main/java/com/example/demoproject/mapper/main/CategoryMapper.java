package com.example.demoproject.mapper.main;

import com.example.demoproject.dto.main.category.CategoryCreateDTO;
import com.example.demoproject.dto.main.category.CategoryDTO;
import com.example.demoproject.dto.main.category.CategoryUpdateDTO;
import com.example.demoproject.entity.main.Category;
import com.example.demoproject.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper extends BaseMapper<Category, CategoryDTO, CategoryCreateDTO, CategoryUpdateDTO> {
}
