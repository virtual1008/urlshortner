package com.Project.UrlShortner.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "short_urls",
        indexes = @Index(name = "idx_short_code", columnList = "shortCode"))
public class ShortUrl {
    @Id
    private Long id;

    @Column(nullable = false,unique = true)
    private String shortCode;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String originalUrl;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private boolean deleted;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    protected ShortUrl(){
    }

    private ShortUrl(Builder builder){
        this.id = builder.id;
        this.shortCode = builder.shortCode;
        this.originalUrl = builder.originalUrl;
        this.userId = builder.userId;
        this.deleted = builder.deleted;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;

    }
    public static Builder builder(){
         return new Builder();
    }

    public static class Builder{
        private Long id;
        private String shortCode;
        private String originalUrl;
        private String userId;
        private boolean deleted;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(Long id){
            this.id = id;
            return this;
        }
        public Builder shortCode(String shortCode){
            this.shortCode = shortCode;
            return this;
        }
        public Builder originalUrl(String originalUrl){
            this.originalUrl = originalUrl;
            return this;
        }
        public Builder userId(String userId){
            this.userId = userId;
            return this;
        }

        public Builder deleted(boolean deleted){
            this.deleted = deleted;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt){
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt){
            this.updatedAt = updatedAt;
            return this;
        }
        public ShortUrl build(){
            return new ShortUrl(this);
        }
    }
    public Long getId() {
        return id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void updateUrl(String newUrl,String shortCode) {
        if( newUrl != null &&!newUrl.isEmpty())this.originalUrl = newUrl;
        //private String newCustomAlias;
        if(shortCode!=null && !shortCode.isEmpty() )this.shortCode = shortCode;
        this.updatedAt = LocalDateTime.now();
    }

    public void softDelete() {
        this.deleted = true;
        this.updatedAt = LocalDateTime.now();
    }
}




