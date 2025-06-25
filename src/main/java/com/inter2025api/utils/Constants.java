package com.inter2025api.utils;

public class Constants {

    // API constant
    public static final String API_KEY = "7db0ab654926a102d67c9232a6928ce7";
    public static final String USERNAME = "Nicoardbe";
    public static final String PASSWORD = "TestPassword123*";

    //Test context keys
    public static final String REQUEST_TOKEN_CONTEXT = "requestToken";
    public static final String SESSION_ID_CONTEXT = "sessionId";
    public static final String LIST_ID_CONTEXT = "listId";
    public static final String LIST_CONTEXT = "list";
    public static final String MOVIES_CONTEXT = "movies";
    public static final String UPDATED_LIST_CONTEXT = "updatedList";

    //Paths and parameters
    public static final String LIST_ID_PATH = "list_id";
    public static final String REQUEST_TOKEN_PATH = "request_token";
    public static final String SESSION_ID_PATH = "session_id";
    public static final String API_KEY_PARAM = "api_key";
    public static final String SESSION_ID_PARAM = "session_id";   
    public static final String LIST_ID_PATH_PARAM = "list-id";

    //Config.properties keys
    public static final String DELETE_LIST = "list.delete";
    public static final String CREATE_LIST = "list.create";
    public static final String ADD_ITEMS = "list.addItems";
    public static final String GET = "list.get";
    public static final String REQUEST_TOKEN = "session.token";
    public static final String CREATE_SESSION_LOGIN = "session.create.login";
    public static final String CREATE_SESSION = "session.create";
    public static final String REMOVE_ITEMS = "list.removeItems";
    public static final String UPDATE_LIST = "list.update";
    public static final String UPDATE_LIST_ITEMS = "list.updateItems";
    

    // JSON file paths
    public static final String MOVIE_LIST_JSON = "src/test/resources/data/items.json";
    

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