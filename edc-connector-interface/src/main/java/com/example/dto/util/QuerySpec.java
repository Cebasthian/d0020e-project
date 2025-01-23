package com.example.dto.util;

import java.util.List;

public class QuerySpec {
    public Integer offset;
    public Integer limit;
    public SortOrder sortOrder;
    public String sortField;
    public List<Object> filterExpressions;

    public QuerySpec(Integer offset, Integer limit, SortOrder sortOrder, String sortField, List<Object> filterExpressions) {
        this.offset = offset;
        this.limit = limit;
        this.sortOrder = sortOrder;
        this.sortField = sortField;
        this.filterExpressions = filterExpressions;
    }

    public enum SortOrder {
        DESC("DESC"), ASC("ASC");

        private SortOrder(String order) {

        }
    }
}
