package com.inter2025api.utils;

public class Constants {

    // Example API key constant
    public static final String API_KEY = "7db0ab654926a102d67c9232a6928ce7";
    public static final String USERNAME = "Nicoardbe";
    public static final String PASSWORD = "TestPassword123*";

    // Timeout values (in milliseconds)
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    // Common HTTP headers
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_AUTHORIZATION = "Authorization";

    // Content types
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_XML = "application/xml";

    // Status codes
    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_FORBIDDEN = 403;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_INTERNAL_SERVER_ERROR = 500;


    private Constants() {
        throw new UnsupportedOperationException("Constants class cannot be instantiated");
    }
}