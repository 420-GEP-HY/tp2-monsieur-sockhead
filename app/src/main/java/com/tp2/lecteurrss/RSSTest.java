package com.tp2.lecteurrss;

public class RSSTest
{
    public static void main(String[] argv)
    {
        SiteRSS bd_sites = new SiteRSS();
        bd_sites.parseRSS("https://ici.radio-canada.ca/rss/4159");
    }
}
