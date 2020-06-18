package com.opsly.hometest.model;

public class FacebookObject {
      private String name;
      private String status;
    
    public FacebookObject(String name, String status) {
        this.name = name;
        this.status = status;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FacebookObject{");
        sb.append("name='").append(name).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
