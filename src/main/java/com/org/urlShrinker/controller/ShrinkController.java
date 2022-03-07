package com.org.urlShrinker.controller;


import com.org.urlShrinker.service.ShrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShrinkController {

    @Autowired
    ShrinkService shrinkService;

    //longtoshort without security
    public String longToShortSingleClick(String longUrl){
        String url = shrinkService.longToShortSingleClick(longUrl);
        return "Shorturl created : http://localhost:8080/"+url;
    }

    //longtoshort with security

    //shorttolong
    public String shortToLong(String shortUrl){
        String longUrl = shrinkService.shortToLong(shortUrl);
        return "LongUrl to redirect : "+longUrl;
    }
}
