package com.tp2.lecteurrss;

import java.io.Serializable;

public class MediaRSS implements Serializable{
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    MediaRSS() {}

    MediaRSS(String URL, String Type)
    {
        url = URL;
        type = Type;
    }
}
