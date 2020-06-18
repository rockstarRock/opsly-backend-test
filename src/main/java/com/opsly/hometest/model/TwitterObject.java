package com.opsly.hometest.model;

public class TwitterObject {
    private String username;
    private String tweet;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getTweet() {
        return tweet;
    }
    
    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InstagramObject{");
        sb.append("username='").append(username).append('\'');
        sb.append(", picture='").append(tweet).append('\'');
        sb.append('}');
        return sb.toString();
    }
    
}
