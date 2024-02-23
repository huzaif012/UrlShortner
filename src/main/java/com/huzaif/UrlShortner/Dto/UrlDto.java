package com.huzaif.UrlShortner.Dto;

import org.springframework.stereotype.Component;

@Component
public class UrlDto {

    String longUrl;


    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
