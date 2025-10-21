package com.example.demoproject.repository.het.transformer;

import com.example.demoproject.criteria.het.TransformerCriteria;
import com.example.demoproject.dao.GenericDao;
import com.example.demoproject.entity.het.Transformer;
import com.example.demoproject.repository.GenericRepository;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TransformerRepositoryImpl extends GenericDao<Transformer, String, TransformerCriteria> implements TransformerRepository {

    @Override
    protected void defineCriteriaOnQuerying(TransformerCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {


        if (utils.isNotEmpty(criteria.get())) {
            whereCause.add("lower(t.name) like lower('%' || :name || '%')");
            params.put("name", criteria.getName());
        }

        super.defineCriteriaOnQuerying(criteria, whereCause, params, queryBuilder);
    }

}
