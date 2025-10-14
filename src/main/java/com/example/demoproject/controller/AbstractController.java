package com.example.demoproject.controller;


import com.example.demoproject.service.GenericService;

public abstract class AbstractController<S extends GenericService> {

    protected static final String API = "/api";
    protected static final String VERSION = "/v1";
    protected static final String VERSION2 = "/v2";
    protected static final String SERVICE_NAME_RAMS = "/rams";
    protected static final String SERVICE_NAME_BIRDARCHA = "/birdarcha";
    public static final String PATH_RAMS = API + VERSION + SERVICE_NAME_RAMS;
    public static final String PATH_RAMS_V2 = API + VERSION2 + SERVICE_NAME_RAMS;
    public static final String PATH_BIRDARCHA = API + VERSION + SERVICE_NAME_BIRDARCHA;

    protected final S service;

    protected AbstractController(S service) {
        this.service = service;
    }

}
