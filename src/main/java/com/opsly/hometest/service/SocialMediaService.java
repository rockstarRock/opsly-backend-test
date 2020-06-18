package com.opsly.hometest.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opsly.hometest.model.FacebookObject;
import com.opsly.hometest.model.InstagramObject;
import com.opsly.hometest.model.TwitterObject;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class SocialMediaService {
    private static final Logger logger = LoggerFactory.getLogger(SocialMediaService.class);
    
    private final String TWITTER_SERVICE_URL = "http://takehome.io/twitter";
    private final String FACEBOOK_SERVICE_URL = "http://takehome.io/facebook";
    private final String INSTAGRAM_SERVICE_URL = "http://takehome.io/instagram";
    
    @Async
    public CompletableFuture<List<String>> getTwitterData() throws IOException {
        
        CloseableHttpClient httpClient = HttpClients.custom()
                                                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                                                    .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        
        ObjectMapper objectMapper = new ObjectMapper();
    
        List<String> tweetList = new ArrayList<String>();
        try {
            String twitterData = restTemplate.getForObject(TWITTER_SERVICE_URL, String.class);
            
            if (validJSon(twitterData)) {
                logger.debug("Twitter Data : ***************\n" + twitterData);
                List<TwitterObject> listTwitter = objectMapper.readValue(twitterData, new TypeReference<List<TwitterObject>>() {});
                tweetList = listTwitter.stream().map(entity -> entity.getTweet()).collect(Collectors.toList());
            }
        }catch (Exception e) {
            //Do nothing as we will be returning empty List
        }    finally {
            return CompletableFuture.completedFuture(tweetList);
        }
    }
    
    @Async
    public CompletableFuture<List<String>> getFacebookData() throws IOException {
        
        CloseableHttpClient httpClient = HttpClients.custom()
                                                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                                                    .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        List<String> fbStatusList = new ArrayList<String>();
        try {
            String fbData = restTemplate.getForObject(FACEBOOK_SERVICE_URL, String.class);
            
            if (validJSon(fbData)) {
                logger.debug("Facebook Data : ***************\n" + fbData);
                List<FacebookObject> listFB = objectMapper.readValue(fbData, new TypeReference<List<FacebookObject>>() {});
                fbStatusList = listFB.stream().map(entity -> entity.getStatus()).collect(Collectors.toList());
            }
        }catch (Exception e) {
            //Do nothing as we will be returning empty List
        }    finally {
            return CompletableFuture.completedFuture(fbStatusList);
        }
    }
    
    @Async
    public CompletableFuture<List<String>> getInstagramData() throws IOException {
        
        CloseableHttpClient httpClient = HttpClients.custom()
                                                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                                                    .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        List<String> instagramPhotosList = new ArrayList<String>();
        try {
            String instagramData = restTemplate.getForObject(INSTAGRAM_SERVICE_URL, String.class);
            
            if (validJSon(instagramData)) {
                logger.debug("Instagram Data : ***************\n" + instagramData);
                List<InstagramObject> listInstagram = objectMapper.readValue(instagramData, new TypeReference<List<InstagramObject>>() {});
                instagramPhotosList = listInstagram.stream().map(entity -> entity.getPhoto()).collect(Collectors.toList());
            }
        }catch (Exception e) {
            //Do nothing as we will be returning empty List
        }    finally {
            return CompletableFuture.completedFuture(instagramPhotosList);
        }
    }
    
    private boolean validJSon(String jsonString) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
