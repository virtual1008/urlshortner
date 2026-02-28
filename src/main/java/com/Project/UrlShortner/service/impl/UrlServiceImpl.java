package com.Project.UrlShortner.service.impl;

import com.Project.UrlShortner.domain.ShortUrl;
import com.Project.UrlShortner.exception.UnauthorizedException;
import com.Project.UrlShortner.exception.UrlNotFoundException;
import com.Project.UrlShortner.id.IdGenerator;
import org.springframework.stereotype.Service;
import com.Project.UrlShortner.repository.UrlRepository;
import com.Project.UrlShortner.service.UrlService;
import com.Project.UrlShortner.util.Base62Encoder;

import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository repository;
    private final IdGenerator idGenerator;

    public UrlServiceImpl(UrlRepository repository , IdGenerator idGenerator){
        this.repository = repository;
        this.idGenerator = idGenerator;
    }
    @Override
    public String createShortUrl(String originalUrl, String userId, String customAlias) {
        String shortCode;
        if(customAlias!=null && !customAlias.isEmpty()){
            if(repository.existsByShortCode(customAlias)){
                throw new RuntimeException("Alias already exists");
            }
            shortCode = customAlias;
        }else{
            long id = idGenerator.generatedId()  & 0x0FFFFFFFFFL;
            shortCode = Base62Encoder.encode(id);
        }
        long id = idGenerator.generatedId() & 0x0FFFFFFFFFL;
        ShortUrl shortUrl = ShortUrl.builder()
                .id(id)
                .shortCode(shortCode)
                .originalUrl(originalUrl)
                .userId(userId)
                .deleted(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        repository.save(shortUrl);
        return shortCode;
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        ShortUrl shortUrl = repository.findByShortCode(shortCode)
                .orElseThrow(()->new UrlNotFoundException("URL not found"));

        if(shortUrl.isDeleted()){
            throw  new UrlNotFoundException("URL deleted");
        }
        return shortUrl.getOriginalUrl();
    }

    @Override
    public void updateUrl(String shortCode, String userId, String newUrl,String customAlias) {
        ShortUrl shortUrl = repository.findByShortCode(shortCode)
                .orElseThrow(()->new UrlNotFoundException("URL not found"));


        if(!shortUrl.getUserId().equals(userId)){
            throw  new UnauthorizedException("Not owner");
        }
//        if (newUrl != null && !newUrl.isEmpty()) {
//            shortUrl.setOriginalUrl(request.getNewUrl());
//        }
        shortUrl.updateUrl(newUrl,customAlias);
        repository.save(shortUrl);
    }

    @Override
    public void deleteUrl(String shortCode, String userId) {
          ShortUrl shortUrl = repository.findByShortCode(shortCode)
                  .orElseThrow(()-> new UrlNotFoundException("URL not found"));

          if(!shortUrl.getUserId().equals(userId)){
              throw new UnauthorizedException("Not owner");
          }

          shortUrl.softDelete();
          repository.save(shortUrl);
    }
}
