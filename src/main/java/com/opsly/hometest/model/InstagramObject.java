package com.opsly.hometest.model;

public class InstagramObject {
    private String username;
    private String photo;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPhoto() {
        return photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InstagramObject{");
        sb.append("username='").append(username).append('\'');
        sb.append(", picture='").append(photo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
