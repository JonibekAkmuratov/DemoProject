package com.example.demoproject.repository.main.impl;

import com.example.demoproject.criteria.main.CategoryCriteria;
import com.example.demoproject.criteria.main.ProductCriteria;
import com.example.demoproject.dao.GenericDao;
import com.example.demoproject.entity.main.Product;
import com.example.demoproject.repository.main.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class ProductRepositoryImpl extends GenericDao<Product, Long, ProductCriteria> implements ProductRepository {

    @Override
    protected StringBuilder joinStringOnQuerying(ProductCriteria criteria) {
        if (criteria.getCategoryId() != null) {
            return new StringBuilder(" LEFT JOIN t.category c ");
        }
        return super.joinStringOnQuerying(criteria);
    }

    @Override
    protected void defineCriteriaOnQuerying(ProductCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {

        if (utils.isNotEmpty(criteria.getName())) {
            whereCause.add("lower(t.name) like lower('%' || :name || '%')");
            params.put("name", criteria.getName());
        }

        if (utils.isNotEmpty(criteria.getCategoryId())) {
            whereCause.add("c.id = :categoryId");
            params.put("categoryId", criteria.getCategoryId());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

}
