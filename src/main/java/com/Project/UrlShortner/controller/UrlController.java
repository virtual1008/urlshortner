package com.Project.UrlShortner.controller;

import com.Project.UrlShortner.dto.CreateUrlRequest;
import com.Project.UrlShortner.dto.CreateUrlResponse;
import com.Project.UrlShortner.dto.UpdateUrlRequest;
import org.springframework.web.bind.annotation.*;
import com.Project.UrlShortner.service.UrlService;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service){
        this.service = service;
    }

    @PostMapping
    public CreateUrlResponse create(
            @RequestHeader("X-USER-ID") String userId,
            @RequestBody CreateUrlRequest request
            ){
        String shortCode = service.createShortUrl(
                request.getOriginalUrl(),
                userId,
                request.getCustomAlias()
        );
        return new CreateUrlResponse(shortCode);
    }

    @PutMapping("/{shortCode}")
    public void update(
            @PathVariable String shortCode,
            @RequestHeader("X-USER-ID") String userId,
            @RequestBody UpdateUrlRequest request
            ){

        service.updateUrl(shortCode,userId,request.getNewUrl(),request.getCustomAlias());
    }

    @DeleteMapping("/{shortCode}")
    public void delete(
            @PathVariable String shortCode,
            @RequestHeader("X-USER-ID") String userId
    ){
        service.deleteUrl(shortCode, userId);
    }
}


