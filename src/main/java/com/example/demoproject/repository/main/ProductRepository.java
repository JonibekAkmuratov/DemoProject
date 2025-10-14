package com.example.demoproject.repository.main;

import com.example.demoproject.criteria.main.ProductCriteria;
import com.example.demoproject.entity.main.Product;
import com.example.demoproject.repository.GenericCrudRepository;

public interface ProductRepository extends GenericCrudRepository<Product, Long, ProductCriteria>  {

}
