package com.example.demoproject.repository.main.impl;

import com.example.demoproject.criteria.main.ProductCriteria;
import com.example.demoproject.dao.GenericDao;
import com.example.demoproject.entity.main.Product;
import com.example.demoproject.repository.main.ProductRepository;
import org.springframework.stereotype.Repository;


@Repository
public class ProductRepositoryImpl extends GenericDao<Product, Long, ProductCriteria>  implements ProductRepository {

}
