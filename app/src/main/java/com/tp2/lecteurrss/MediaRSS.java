package com.tp2.lecteurrss;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

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

    public MediaRSS()
    {
        url = null;
        type = null;
    }

    public MediaRSS(String URL, String Type)
    {
        url = URL;
        type = Type;
    }

    public boolean verifImage(String imageURL)
    {
        try
        {
            URL url = new URL(imageURL);
            InputStream is = url.openStream();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
