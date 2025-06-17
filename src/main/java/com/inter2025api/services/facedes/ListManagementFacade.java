package com.inter2025api.services.facedes;

import com.inter2025api.context.TestContext;
import com.inter2025api.services.impl.ListManagementImpl;
import com.inter2025api.services.entity.ListManagementService;

import io.restassured.response.Response;

//Uses ListrManagementService to handle list management operations through the implementation class ListManagementImpl.
public class ListManagementFacade {
    private final ListManagementService listManagementService = new ListManagementImpl();
    private final TestContext testContext;

    public ListManagementFacade(TestContext testContext) {
        this.testContext = testContext;
    }

    public Response createList(String name, String description, boolean isPublic) {
        String sessionId = (String) testContext.get("sessionId");
        return listManagementService.createList(sessionId, name, description, isPublic);
    }
}