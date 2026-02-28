package com.Project.UrlShortner.dto;

public class CreateUrlRequest {
    private String originalUrl;
    private String customAlias;

    private CreateUrlRequest(){}

    public String getOriginalUrl(){
        return originalUrl;
    }

    public String getCustomAlias(){
        return customAlias;
    }
}
