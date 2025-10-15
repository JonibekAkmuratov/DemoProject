package com.example.demoproject.repository.main.impl;

import com.example.demoproject.criteria.main.CategoryCriteria;
import com.example.demoproject.dao.GenericDao;
import com.example.demoproject.entity.main.Category;
import com.example.demoproject.repository.GenericCrudRepository;
import com.example.demoproject.repository.main.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class CategoryRepositoryImpl extends GenericDao<Category, Long, CategoryCriteria> implements CategoryRepository {


    @Override
    protected void defineCriteriaOnQuerying(CategoryCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {

        if (utils.isNotEmpty(criteria.getName())) {
            whereCause.add("lower(t.name) like lower('%' || :name || '%')");
            params.put("name", criteria.getName());
        }



        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
