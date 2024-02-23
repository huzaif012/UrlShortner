package com.huzaif.UrlShortner.service;

import com.huzaif.UrlShortner.entity.Url;
import com.huzaif.UrlShortner.repository.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UrlServicImpl implements UrlService{

    @Autowired
    UrlRepo urlRepo;


    @Override
    public String hashUrl(String url) {
        try {
            // Create a SHA-256 MessageDigest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Update the digest with the bytes of the URL
            byte[] hashBytes = digest.digest(url.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            // Limit the hash to return a string length of 6
            String hashedUrl = hexString.toString().substring(0, 6);

            return hashedUrl;
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception (e.g., log an error)
            e.printStackTrace();
            return null; // Return null in case of error
        }
    }

    @Override
    public Url saveUrl(Url url) {
        return urlRepo.save(url);
    }

    @Override
    public Url findUrl(String shorturl) {
        return urlRepo.findByShortUrl(shorturl);
    }

    @Override
    public boolean UrlExist(String longUrl) {
        return urlRepo.existsByLongUrl(longUrl);
    }


}
