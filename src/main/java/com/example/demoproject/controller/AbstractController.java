package com.example.demoproject.controller;


import com.example.demoproject.service.GenericService;

public abstract class AbstractController<S extends GenericService> {

    protected static final String API = "/api";
    protected static final String VERSION = "/v1";
    public static final String PATH_DEMO = API + VERSION;

    protected final S service;

    protected AbstractController(S service) {
        this.service = service;
    }

}
