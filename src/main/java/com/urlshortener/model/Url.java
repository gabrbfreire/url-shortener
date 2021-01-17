package com.urlshortener.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "urls")
public class Url {

    @Id
    private String id;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
