package com.github.phongnt.train.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
public class PageResponse<T> {
    private long totalItems;
    private int totalPages;
    private int currentPage;
    @Builder.Default
    private List<T> trains = new ArrayList<>();
}
