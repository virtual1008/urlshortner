package com.Project.UrlShortner.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.Project.UrlShortner.service.UrlService;

import java.io.IOException;

@RestController
public class RedirectController {
    private final UrlService service;

    public RedirectController(UrlService service) {
        this.service = service;
    }

    @GetMapping("/{shortCode}")
    public void redirect(@PathVariable String shortCode,
                         HttpServletResponse response)
            throws IOException {

        String originalUrl = service.getOriginalUrl(shortCode);
        response.sendRedirect(originalUrl);
    }

}
