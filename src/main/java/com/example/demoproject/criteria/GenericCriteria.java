package com.example.demoproject.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class GenericCriteria {
    protected String sortBy;
    protected String sortDirection;
    protected boolean   ignoreDeletion = false;
    private Number selfId;
    private Integer page = 0;
    private Integer size = 30;
    private String allSearch;

    public GenericCriteria(Number selfId, Integer page, Integer size, String sortBy, String sortDirection, String allSearch) {
        this.selfId = selfId;
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
        this.allSearch = allSearch;
    }

    public String getSortDirection() {
        return sortDirection == null || sortDirection.isEmpty() ? "asc" : sortDirection;
    }
}
