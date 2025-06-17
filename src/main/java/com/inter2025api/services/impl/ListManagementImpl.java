package com.inter2025api.services.impl;

import java.util.List;

import com.inter2025api.services.entity.ListManagementService;

import io.restassured.response.Response;

public class ListManagementImpl implements ListManagementService{

    @Override
    public Response createList(String sessionId, String name, String description, boolean isPublic) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createList'");
    }

    @Override
    public Response addItemToList(String sessionId, String listId, String mediaId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItemToList'");
    }

    @Override
    public Response removeItemFromList(String sessionId, String listId, String mediaId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeItemFromList'");
    }

    @Override
    public Response getListDetails(String sessionId, String listId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getListDetails'");
    }

    @Override
    public Response getListItems(String sessionId, String listId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getListItems'");
    }

    @Override
    public Response deleteList(String sessionId, String listId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteList'");
    }

    @Override
    public Response updateList(String sessionId, String listId, String name, String description, boolean isPublic) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateList'");
    }
    
}
