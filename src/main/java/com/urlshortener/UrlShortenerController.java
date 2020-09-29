package com.urlshortener;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortenerController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getShortUrl(@PathVariable() String id){
        try {
            return new ResponseEntity<String>(urlService.getShortUrl(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<String> createShortUrl(@RequestParam("url") String url){

        try{
            UrlValidator urlValidator = new UrlValidator(
                    new String[]{"http", "https"}
            );

            if(urlValidator.isValid(url)){
                return new ResponseEntity<String>(urlService.createShortUrl(url), HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Invalid URL", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
