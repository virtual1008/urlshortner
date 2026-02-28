package com.Project.UrlShortner.service;

public interface UrlService {
    String createShortUrl(String originalUrl , String userId ,String customAlias);

    String getOriginalUrl(String shortCode);

    void updateUrl(String shortCode, String userId,String newUrl,String customAlias);

    void deleteUrl(String shortCode, String userId);
}
