package com.opsly.hometest.restcontroller;

import com.opsly.hometest.service.SocialMediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value="/")
public class SocialMediaRestController {
    private static final Logger logger = LoggerFactory.getLogger(SocialMediaRestController.class);

    @Autowired
    SocialMediaService service;
    
    @RequestMapping(value="test",method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public String getSocialDataX() {
        return "hello";
    }
    
    @RequestMapping(value="",method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, List<String>> getSocialData() {
        Map<String, List<String>> returnMap = new HashMap<String, List<String>>();
        
        // Start the timer
        Instant start = Instant.now();
        Instant finish = null;
    
        try {
            //Make the asynchronous calls
            CompletableFuture<List<String>> twitterData = service.getInstagramData();
            CompletableFuture<List<String>> facebookData = service.getFacebookData();
            CompletableFuture<List<String>> instagramData = service.getInstagramData();
    
            // Wait until they are all done
            CompletableFuture.allOf(twitterData, facebookData, instagramData).join();
            
            returnMap.put("twitter", twitterData.get());
            returnMap.put("facebook", facebookData.get());
            returnMap.put("instagram", instagramData.get());
            finish = Instant.now();
            
            returnMap.put("<timetaken_to_execute>", Arrays.asList(Duration.between(start, finish).toMillis() + ""));
        } catch (Exception e) {
            //Do nothing for now
        } finally {
            logger.info("Elapsed time: " + Duration.between(start, finish).toMillis());
            return returnMap;
        }
    }
}
