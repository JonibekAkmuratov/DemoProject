package com.example.demoproject.service.main.product;


import com.example.demoproject.criteria.main.ProductCriteria;
import com.example.demoproject.dto.main.product.ProductCreateDTO;
import com.example.demoproject.dto.main.product.ProductDTO;
import com.example.demoproject.dto.main.product.ProductUpdateDTO;
import com.example.demoproject.service.GenericCrudService;
import org.springframework.stereotype.Service;

@Service
public interface ProductService extends GenericCrudService<ProductDTO, ProductCreateDTO, ProductUpdateDTO, ProductCriteria, Long> {
}
