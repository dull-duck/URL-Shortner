package com.org.urlShrinker.service;

import com.org.urlShrinker.constants.ShrinkConstants;
import com.org.urlShrinker.model.ShrinkModel;
import com.org.urlShrinker.repository.ShrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShrinkService {

    @Value("${Elements}")
    private String elements;

    @Value("${LengthOfShortUrl}")
    private int lengthOfShortUrl;

    @Autowired
    ShrinkRepository shrinkRepository;

    @Autowired
    ShrinkConstants shrinkConstants;


    //longtoshort without security
    public String longToShortSingleClick(String longUrl){
        ShrinkModel result = hashingUrl(longUrl);
        result.setSecurityFlag("Y");
        return result.getShortUrl();
    }

    //longtoshort with security
    public String longToShortMultipleClick(String longUrl){
        ShrinkModel result = hashingUrl(longUrl);
        result.setSecurityFlag("N");
        return result.getShortUrl();
    }

    //shorttolong
    public String shortToLong(String shortUrl) {
        ShrinkModel model=searchDb(shortUrl);
        if (null != model) {
            String securityflag = model.getSecurityFlag();
            if (securityflag.equalsIgnoreCase("Y")) {
                shrinkRepository.deleteById(shortUrl);
            }
            return model.getLongUrl();
        } else {
            return shrinkConstants.redirectMessage;
        }
    }


    private ShrinkModel hashingUrl(String longUrl){
        char [] result = new char[lengthOfShortUrl];
        Random random = new Random();
        for(int i=0; i<= lengthOfShortUrl-1;i++) {
            int randomNumber = random.nextInt((elements.length() - 1));
            result[i]= elements.charAt(randomNumber);
        }
        String urlGenerated = result.toString();
        ShrinkModel model = searchDb(urlGenerated);
        if(null == model){
            ShrinkModel newModel = new ShrinkModel();
            newModel.setLongUrl(longUrl);
            newModel.setShortUrl(urlGenerated);
            newModel.setSecurityFlag("");
            shrinkRepository.save(newModel);
            return newModel;
        }else if(null != model){
            hashingUrl(longUrl);
        }
        return null;
    }

    private ShrinkModel searchDb(String shortUrl) {
        ShrinkModel model = shrinkRepository.findById(shortUrl).get();
        return model;
    }
}
