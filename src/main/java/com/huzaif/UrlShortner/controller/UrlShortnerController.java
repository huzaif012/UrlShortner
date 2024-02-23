package com.huzaif.UrlShortner.controller;

import com.huzaif.UrlShortner.Dto.UrlDto;
import com.huzaif.UrlShortner.entity.Url;
import com.huzaif.UrlShortner.repository.UrlRepo;
import com.huzaif.UrlShortner.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UrlShortnerController {
    @Autowired
    UrlService urlService;
    @Autowired
    UrlRepo urlRepo;
    @Autowired
    UrlDto urlDto;
    @Autowired
    private HttpServletRequest request;
    @PostMapping("/shortUrl")
    private ResponseEntity<?> urlShortner(@RequestBody UrlDto urlDto){
        String shortUrl = urlService.hashUrl(urlDto.getLongUrl());
        Url url = new Url();
        url.setLongUrl(urlDto.getLongUrl());
        url.setShortUrl(shortUrl);
        if(urlService.UrlExist(urlDto.getLongUrl())){
            return ResponseEntity.ok("Short version of URL already exists: "+request.getRequestURL()+"/"+url.getShortUrl());

        }else {
            urlService.saveUrl(url);
            return ResponseEntity.ok("URL saved successfully \n here is the short version of url: " + request.getRequestURL() + "/" + url.getShortUrl());
        }
    }

    @GetMapping("/shortUrl/{shortUrl}")
    public ResponseEntity<HttpStatus> gettingUrlfromShortUrl(@PathVariable String shortUrl) {
        // Use the short URL from the request body
        Url url = urlService.findUrl(shortUrl);
        String uri = url.getLongUrl();
        //val target = service.resolve(hash)

        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(uri))
                .header(HttpHeaders.CONNECTION, "close")
                .build();
    }
}
