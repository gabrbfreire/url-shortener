package com.urlshortener.controller;

import com.urlshortener.service.UrlService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlShortenerController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/g/{id}")
    public RedirectView getShortUrl(@PathVariable() String id){
        try {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(urlService.getShortUrl(id));
            return redirectView;
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping("/create")
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
