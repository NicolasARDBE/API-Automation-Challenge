package com.inter2025api.models;

import lombok.Setter;
import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Setter
@Getter
@NoArgsConstructor
@Builder

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    @JsonProperty("media_type")
    String mediaType;
    @JsonProperty("media_id")
    String mediaId;
}