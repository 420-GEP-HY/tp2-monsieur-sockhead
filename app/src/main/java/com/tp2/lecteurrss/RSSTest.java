package com.tp2.lecteurrss;

import java.util.ArrayList;
import java.util.List;

public class RSSTest
{
    public static void main(String[] argv)
    {
        List<SiteRSS> bd_sites = new ArrayList<SiteRSS>();

        try
        {
            bd_sites.add(new SiteRSS("https://ici.radio-canada.ca/rss/4159"));
        }
        catch(Exception e)
        {
            System.out.println("Erreur!");
        }

        try
        {
            bd_sites.add(new SiteRSS("je suis un url invalide"));
        }
        catch(Exception e)
        {
            System.out.println("Erreur!");
        }

        try
        {
            bd_sites.add(new SiteRSS("https://je ne suis pas valide non plus."));
        }
        catch(Exception e)
        {
            System.out.println("Erreur!");
        }

        try
        {
            bd_sites.add(new SiteRSS("http://je-nexiste-pas.com"));
        }
        catch(Exception e)
        {
            System.out.println("Erreur!");
        }
    }
}
