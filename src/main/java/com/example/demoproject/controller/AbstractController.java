package com.example.demoproject.controller;


import com.example.demoproject.service.GenericService;

public abstract class AbstractController<S extends GenericService> {

    protected static final String API = "/api";
    protected static final String VERSION = "/v1";
    protected static final String SERVICE_NAME_RAMS = "/demo";
    public static final String PATH_DEMO = API + VERSION + SERVICE_NAME_RAMS;

    protected final S service;

    protected AbstractController(S service) {
        this.service = service;
    }

}
