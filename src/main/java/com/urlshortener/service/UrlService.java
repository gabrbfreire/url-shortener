package com.urlshortener.service;

import com.google.common.hash.Hashing;
import com.urlshortener.model.Url;
import com.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String getShortUrl(String id){
       Optional<Url> urlOptional = urlRepository.findById(id);

       if(urlOptional.isPresent()){
           return urlOptional.get().getUrl();
       }else {
           return "urlNotFound";
       }
    }

    public String createShortUrl(String longUrl){
        Url url = new Url();
        String id = Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
        url.setId(id);
        url.setUrl(longUrl);
        urlRepository.save(url);
        return id;
    }
}
