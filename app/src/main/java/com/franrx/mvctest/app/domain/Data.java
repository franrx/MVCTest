package com.franrx.mvctest.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {

    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;
    private List<Result> results = new ArrayList<Result>();

    /**
     * @return The offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * @param offset The offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * @return The limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * @param limit The limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * @return The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

}