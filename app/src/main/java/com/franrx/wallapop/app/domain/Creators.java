package com.franrx.wallapop.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Creators implements Serializable {

    private Integer available;
    private String collectionURI;
    private List<CreatorItem> creatorItems = new ArrayList<CreatorItem>();
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
     * @return The creatorItems
     */
    public List<CreatorItem> getCreatorItems() {
        return creatorItems;
    }

    /**
     * @param creatorItems The creatorItems
     */
    public void setCreatorItems(List<CreatorItem> creatorItems) {
        this.creatorItems = creatorItems;
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