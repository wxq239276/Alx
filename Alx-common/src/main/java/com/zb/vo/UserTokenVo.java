package com.zb.vo;

public class UserTokenVo {
    private String token;
    private long expTime;
    private long currTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpTime() {
        return expTime;
    }

    public void setExpTime(long expTime) {
        this.expTime = expTime;
    }

    public long getCurrTime() {
        return currTime;
    }

    public void setCurrTime(long currTime) {
        this.currTime = currTime;
    }
}
