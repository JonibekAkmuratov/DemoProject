package com.example.demoproject.repository.main.impl;

import com.example.demoproject.criteria.main.CategoryCriteria;
import com.example.demoproject.dao.GenericDao;
import com.example.demoproject.entity.main.Category;
import com.example.demoproject.repository.GenericCrudRepository;
import com.example.demoproject.repository.main.CategoryRepository;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryRepositoryImpl extends GenericDao<Category, Long, CategoryCriteria> implements CategoryRepository {

}
