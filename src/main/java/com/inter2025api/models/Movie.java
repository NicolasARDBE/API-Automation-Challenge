package com.inter2025api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @JsonProperty("id")
    private int id;

    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("media_id")
    private int mediaId;

    @JsonProperty("comment")
    private String comment;
}