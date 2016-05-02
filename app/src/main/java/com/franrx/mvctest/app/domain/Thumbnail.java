package com.franrx.mvctest.app.domain;

import java.io.Serializable;

public class Thumbnail implements Serializable {

    private String path;
    private String extension;

    /**
     * @return The path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path The path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return The extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param extension The extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

}