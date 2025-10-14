package com.example.demoproject.service;

import com.example.demoproject.mapper.GenericMapper;
import com.example.demoproject.repository.Repository;


public abstract class AbstractService<R extends Repository, M extends GenericMapper> {

    protected final R repository;
    protected final M mapper;

    protected AbstractService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
