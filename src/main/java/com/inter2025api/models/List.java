package com.inter2025api.models;

import lombok.Setter;
import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@Builder

public class List {
    private String id;
    private String name;
    private String description;
    private boolean isPublic;
}