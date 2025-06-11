package com.impuls.business_service.controllers;

public class ApiPaths {

    public static final String API_BASE = "/api";
    public static final String CURRENT_API_VERSION = "/v1";
    public static final String API_PREFIX = API_BASE + CURRENT_API_VERSION;

    public static final String ADMIN_PATH = API_PREFIX + "/admin/**";
    public static final String USER_PATH = API_PREFIX + "/user/**";
    public static final String PUBLIC_PATH = API_PREFIX + "/public/**";
}
