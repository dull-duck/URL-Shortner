package com.org.urlShrinker.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table
@Entity
public class ShrinkModel {

    @Id
    private String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getSecurityFlag() {
        return securityFlag;
    }

    public void setSecurityFlag(String securityFlag) {
        this.securityFlag = securityFlag;
    }

    @Column
    private String longUrl;

    @Column
    private String securityFlag;
}
