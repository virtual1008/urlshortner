package com.Project.UrlShortner.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateUrlRequest {
    @NotBlank(message = "New URL must not be blank")
    private String newUrl;

    private String customAlias;
    public UpdateUrlRequest() {

    }

    public String getNewUrl() {
        return newUrl;
    }

    public String getCustomAlias(){
        return  customAlias;
    }
}
