package com.urlshortener.controller.form;

import org.hibernate.validator.constraints.URL;

public class UrlForm {

    @URL(protocol = "http")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
