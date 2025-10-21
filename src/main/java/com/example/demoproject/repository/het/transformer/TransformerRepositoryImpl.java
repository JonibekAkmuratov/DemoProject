package com.example.demoproject.repository.het.transformer;

import com.example.demoproject.criteria.het.TransformerCriteria;
import com.example.demoproject.dao.GenericDao;
import com.example.demoproject.entity.het.Transformer;
import com.example.demoproject.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TransformerRepositoryImpl extends GenericDao<Transformer, String, TransformerCriteria> implements TransformerRepository {
}
