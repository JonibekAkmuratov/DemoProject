package com.example.demoproject.service.main.product;

import com.example.demoproject.criteria.main.ProductCriteria;
import com.example.demoproject.dto.Data;
import com.example.demoproject.dto.main.product.ProductCreateDTO;
import com.example.demoproject.dto.main.product.ProductDTO;
import com.example.demoproject.dto.main.product.ProductUpdateDTO;
import com.example.demoproject.mapper.main.CategoryMapper;
import com.example.demoproject.mapper.main.ProductMapper;
import com.example.demoproject.repository.main.CategoryRepository;
import com.example.demoproject.repository.main.ProductRepository;
import com.example.demoproject.service.AbstractService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ProductServiceImpl extends AbstractService<ProductRepository, ProductMapper> implements ProductService {


    protected ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<Data<List<ProductDTO>>> getAll(@NonNull ProductCriteria criteria) {
        return null;
    }

    @Override
    public ResponseEntity<Data<ProductDTO>> get(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull ProductCreateDTO DTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Boolean>> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(@NonNull ProductUpdateDTO DTO) {
        return null;
    }
}
