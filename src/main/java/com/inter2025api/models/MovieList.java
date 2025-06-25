package com.inter2025api.models;

import lombok.Setter;

import java.util.List;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Setter
@Getter
@NoArgsConstructor
@Builder

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieList {
    private String name;
    private String description;
    private List <Movie> movies;
}