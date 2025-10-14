package com.example.demoproject.repository;


import com.example.demoproject.criteria.GenericCriteria;
import com.example.demoproject.entity.BaseDomain;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface GenericRepository<T extends BaseDomain, ID extends Serializable, C extends GenericCriteria> extends Repository {
    Optional<T> find(ID id);

    Optional<T> find(C criteria);

    List<T> findAll(C criteria);

    Long getTotalCount(C criteria);

}
