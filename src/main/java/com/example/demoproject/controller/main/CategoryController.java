package com.example.demoproject.controller.main;

import com.example.demoproject.controller.AbstractController;
import com.example.demoproject.service.main.category.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class CategoryController extends AbstractController<CategoryService> {


    protected CategoryController(CategoryService service) {
        super(service);
    }



}