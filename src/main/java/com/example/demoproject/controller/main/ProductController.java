package com.example.demoproject.controller.main;

import com.example.demoproject.controller.AbstractController;
import com.example.demoproject.service.main.product.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController extends AbstractController<ProductService> {


    protected ProductController(ProductService service) {
        super(service);
    }


}