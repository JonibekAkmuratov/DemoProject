package com.example.demoproject.controller.main;

import com.example.demoproject.annotation.RateLimit;
import com.example.demoproject.controller.AbstractController;
import com.example.demoproject.criteria.main.CategoryCriteria;
import com.example.demoproject.dto.Data;
import com.example.demoproject.dto.main.category.CategoryCreateDTO;
import com.example.demoproject.dto.main.category.CategoryDTO;
import com.example.demoproject.dto.main.category.CategoryUpdateDTO;
import com.example.demoproject.service.main.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demoproject.controller.AbstractController.PATH_DEMO;

@RestController
@RequestMapping(PATH_DEMO+"/category")
public class CategoryController extends AbstractController<CategoryService> {


    protected CategoryController(CategoryService service) {
        super(service);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Data<CategoryDTO>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    @RateLimit(
            type = RateLimit.RateLimitType.API,
            capacity = 5,
            refillTokens = 5,
            refillDuration = 1,  // 1 daqiqada 5 ta
            keyPattern = "{ip}"
)
    public ResponseEntity<Data<List<CategoryDTO>>> getAll(CategoryCriteria criteria) {
        return service.getAll(criteria);
    }

    @PostMapping
    public ResponseEntity<Data<Long>> create(@NonNull @Valid @RequestBody CategoryCreateDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Data<Boolean>> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping
    public ResponseEntity<Data<Boolean>> update(@NonNull @Valid @RequestBody CategoryUpdateDTO dto) {
        return service.update(dto);
    }


}