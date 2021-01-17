package com.urlshortener.controller;

import com.urlshortener.controller.form.UrlLongForm;
import com.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
public class UrlShortenerController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/g/{id}")
    public RedirectView getShortUrl(@PathVariable String id){
        try {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(urlService.getShortUrl(id));
            if(urlService.getShortUrl(id) == "urlNotFound"){
                redirectView.setUrl("http://localhost:8080");
            }
            return redirectView;
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createShortUrl(@Valid UrlLongForm urlLongForm){
        try{
            return new ResponseEntity<String>(urlService.createShortUrl(urlLongForm.getUrl()), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
