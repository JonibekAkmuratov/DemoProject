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


        // Region filter
        if (utils.isNotEmpty(criteria.getRegionId())) {
            whereCause.add("t.region.id = :regionId");
            params.put("regionId", criteria.getRegionId());
        }

        // District filter
        if (utils.isNotEmpty(criteria.getDistrictId())) {
            whereCause.add("t.district.id = :districtId");
            params.put("districtId", criteria.getDistrictId());
        }

        // Collection date filter
        if (utils.isNotEmpty(criteria.getCollectionDate())) {
            whereCause.add("t.collectionDate = :collectionDate");
            params.put("collectionDate", criteria.getCollectionDate());
        }

        // Level1 filter
        if (utils.isNotEmpty(criteria.getLevel1())) {
            whereCause.add("t.level1 = :level1");
            params.put("level1", criteria.getLevel1());
        }

        super.defineCriteriaOnQuerying(criteria, whereCause, params, queryBuilder);
    }

    @Override
    protected StringBuilder joinStringOnQuerying(TransformerCriteria criteria) {
        StringBuilder joins = new StringBuilder();

        // Region join - if needed
        if (criteria.getRegionId() != null) {
            joins.append(" LEFT JOIN t.region r ");
        }

        // District join - if needed
        if (criteria.getDistrictId() != null) {
            joins.append(" LEFT JOIN t.district d ");
        }

        return joins;
    }

}
