package com.org.urlShrinker.controller;


import com.org.urlShrinker.service.ShrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class ShrinkController {

    @Autowired
    ShrinkService shrinkService;

    //longtoshort without security
    @PostMapping("/longToShortSingleClick")
    public @ResponseBody String longToShortSingleClick(@RequestBody  String longUrl){
        String url = shrinkService.longToShortSingleClick(longUrl);
        return "Shorturl created : http://localhost:8080/"+url;
    }

    //longtoshort with security
    @PostMapping("/longToShortMultipleClick")
    public @ResponseBody String longToShortMultipleClick(@RequestBody  String longUrl){
        String url = shrinkService.longToShortMultipleClick(longUrl);
        return "Shorturl created : http://localhost:8080/"+url;
    }


    //shorttolong
    @GetMapping("/hashUrl/{shortUrl}")
    public @ResponseBody  String shortToLong(@PathVariable String shortUrl){
        String longUrl = shrinkService.shortToLong(shortUrl);
        return "LongUrl to redirect : "+longUrl;
    }
}
