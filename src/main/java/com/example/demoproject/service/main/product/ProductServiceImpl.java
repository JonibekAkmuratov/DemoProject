package com.example.demoproject.service.main.product;

import com.example.demoproject.criteria.main.ProductCriteria;
import com.example.demoproject.dto.Data;
import com.example.demoproject.dto.main.product.ProductCreateDTO;
import com.example.demoproject.dto.main.product.ProductDTO;
import com.example.demoproject.dto.main.product.ProductUpdateDTO;
import com.example.demoproject.entity.main.Product;
import com.example.demoproject.exceptions.ResourceNotFoundException;
import com.example.demoproject.mapper.main.CategoryMapper;
import com.example.demoproject.mapper.main.ProductMapper;
import com.example.demoproject.repository.main.CategoryRepository;
import com.example.demoproject.repository.main.ProductRepository;
import com.example.demoproject.service.AbstractService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

        List<Product> productList = repository.findAll(criteria);
        Long totalCount = repository.getTotalCount(criteria);

        List<ProductDTO> productDTOList = mapper.toDto(productList);

        return new ResponseEntity<>(new Data<>(productDTOList, totalCount), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<ProductDTO>> get(@NonNull Long id) {
        Product product = repository.find(id).orElseThrow(ResourceNotFoundException::new);
        ProductDTO productDTO = mapper.toDto(product);
        return new ResponseEntity<>(new Data<>(productDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<Long>> create(@NonNull ProductCreateDTO productCreateDTO) {

        Product product = mapper.fromCreateDto(productCreateDTO);
        repository.save(product);
        return new ResponseEntity<>(new Data<>(product.getId()), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Data<Boolean>> delete(@NonNull Long id) {
        repository.delete(id);
        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(@NonNull ProductUpdateDTO productUpdateDTO) {

        Product product = repository.find(productUpdateDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        mapper.partialUpdate(productUpdateDTO, product);
        repository.save(product);

        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);
    }
}
