package com.example.demoproject.controller.main;

import com.example.demoproject.controller.AbstractController;
import com.example.demoproject.criteria.main.ProductCriteria;
import com.example.demoproject.dto.Data;
import com.example.demoproject.dto.main.product.ProductCreateDTO;
import com.example.demoproject.dto.main.product.ProductDTO;
import com.example.demoproject.dto.main.product.ProductUpdateDTO;
import com.example.demoproject.service.main.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demoproject.controller.AbstractController.PATH_DEMO;

@RestController
@RequestMapping(PATH_DEMO + "/product")
public class ProductController extends AbstractController<ProductService> {

    protected ProductController(ProductService service) {
        super(service);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Data<ProductDTO>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public ResponseEntity<Data<List<ProductDTO>>> getAll(ProductCriteria criteria) {
        return service.getAll(criteria);
    }

    @PostMapping
    public ResponseEntity<Data<Long>> create(@NonNull @Valid @RequestBody ProductCreateDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Data<Boolean>> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping
    public ResponseEntity<Data<Boolean>> update(@NonNull @Valid @RequestBody ProductUpdateDTO dto) {
        return service.update(dto);
    }

}