package com.huzaif.UrlShortner.service;

import com.huzaif.UrlShortner.entity.Url;

public interface UrlService {

    public String hashUrl(String url);

    public Url saveUrl(Url url);

    public Url findUrl(String shorturl);
    public boolean UrlExist(String longUrl);

}
