package com.inter2025api.models.dtos;

import lombok.Setter;
import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@Builder

public class SessionRequest {
    String username;
    String password;
    String request_token;

    public void setRequestToken(String requestToken) {
        this.request_token = requestToken;
    }
}