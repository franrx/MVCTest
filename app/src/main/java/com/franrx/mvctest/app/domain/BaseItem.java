package com.franrx.mvctest.app.domain;

import java.io.Serializable;

/**
 * Created by fran on 23/2/16.
 */
public class BaseItem implements Serializable {
    private String resourceURI;
    private String name;

    /**
     * @return The resourceURI
     */
    public String getResourceURI() {
        return resourceURI;
    }

    /**
     * @param resourceURI The resourceURI
     */
    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

}