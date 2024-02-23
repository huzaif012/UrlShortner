package com.huzaif.UrlShortner.repository;

import com.huzaif.UrlShortner.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepo extends JpaRepository<Url, Long> {
    public Url findByShortUrl(String shortUrl);
    public boolean existsByLongUrl(String longUrl);
}
