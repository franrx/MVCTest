package com.franrx.mvctest.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stories implements Serializable {

    private Integer available;
    private String collectionURI;
    private List<StoryItem> items = new ArrayList<StoryItem>();
    private Integer returned;

    /**
     * @return The available
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * @param available The available
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     * @return The collectionURI
     */
    public String getCollectionURI() {
        return collectionURI;
    }

    /**
     * @param collectionURI The collectionURI
     */
    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    /**
     * @return The items
     */
    public List<StoryItem> getItems() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setItems(List<StoryItem> items) {
        this.items = items;
    }

    /**
     * @return The returned
     */
    public Integer getReturned() {
        return returned;
    }

    /**
     * @param returned The returned
     */
    public void setReturned(Integer returned) {
        this.returned = returned;
    }

}