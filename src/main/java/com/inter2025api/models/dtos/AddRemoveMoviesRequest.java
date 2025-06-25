package com.inter2025api.models.dtos;
import java.util.List;
import com.inter2025api.models.Movie;

import lombok.Setter;
import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@Builder

public class AddRemoveMoviesRequest {
    private List<Movie> items;

    public AddRemoveMoviesRequest(List<Movie> items) {
        this.items = items;
    }
}