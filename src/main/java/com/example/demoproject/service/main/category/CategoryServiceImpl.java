package com.example.demoproject.service.main.category;

import com.example.demoproject.criteria.main.CategoryCriteria;
import com.example.demoproject.dto.Data;
import com.example.demoproject.dto.main.category.CategoryCreateDTO;
import com.example.demoproject.dto.main.category.CategoryDTO;
import com.example.demoproject.dto.main.category.CategoryUpdateDTO;
import com.example.demoproject.entity.main.Category;
import com.example.demoproject.mapper.main.CategoryMapper;
import com.example.demoproject.repository.main.CategoryRepository;
import com.example.demoproject.service.AbstractService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Slf4j
@Service
public class CategoryServiceImpl extends AbstractService<CategoryRepository, CategoryMapper> implements CategoryService {

    protected CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        super(repository, mapper);
    }


    @Override
    public ResponseEntity<Data<List<CategoryDTO>>> getAll(@NonNull CategoryCriteria criteria) {

        List<Category> categoryList = repository.findAll(criteria);
        Long totalCount = repository.getTotalCount(criteria);

        List<CategoryDTO> categoryDTOList = mapper.toDto(categoryList);

        return new ResponseEntity<>(new Data<>(categoryDTOList, totalCount), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<CategoryDTO>> get(@NonNull Long aLong) {

        Category category = repository.find(aLong).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CategoryDTO categoryDTO = mapper.toDto(category);

        return new ResponseEntity<>(new Data<>(categoryDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull CategoryCreateDTO categoryCreateDTO) {
        Category category = mapper.fromCreateDto(categoryCreateDTO);
        repository.save(category);

        return ResponseEntity.ok(new Data<>(category.getId()));
    }

    @Override
    public ResponseEntity<Data<Boolean>> delete(@NonNull Long id) {
        repository.delete(id);
        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(@NonNull CategoryUpdateDTO categoryUpdateDTO) {
        Category category = repository.find(categoryUpdateDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapper.partialUpdate(categoryUpdateDTO, category);
        repository.save(category);

        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);
    }
}
