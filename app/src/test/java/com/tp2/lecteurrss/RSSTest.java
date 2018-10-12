package com.tp2.lecteurrss;

import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class RSSTest
{
    @Test
    public void TesterRSS()
    {
        List<SiteRSS> bd_sites = new ArrayList<SiteRSS>();
        try
        {
            // Radio-Canada
            bd_sites.add(new SiteRSS("https://ici.radio-canada.ca/rss/4159"));
            bd_sites.add(new SiteRSS("https://ici.radio-canada.ca/rss/1000524"));

            // La Presse
            // Erreur 403 - Tester sur téléphone plus tard
            /*
            bd_sites.add(new SiteRSS("https://www.lapresse.ca/rss/225"));
            bd_sites.add(new SiteRSS("https://www.lapresse.ca/rss/217"));
            bd_sites.add(new SiteRSS("https://www.lapresse.ca/rss/387"));
            */

            // Slashdot
            bd_sites.add(new SiteRSS("http://rss.slashdot.org/Slashdot/slashdotMain")); // On va devoir gérer les liens et les images dans la description

            // CommentCaMarche
            bd_sites.add(new SiteRSS("https://www.commentcamarche.net/rss/"));

            // Developpez
            bd_sites.add(new SiteRSS("https://www.developpez.com/index/rss"));

            // Security Now
            bd_sites.add(new SiteRSS("http://feeds.twit.tv/sn.xml")); // Version MP3
            bd_sites.add(new SiteRSS("http://feeds.twit.tv/sn_video_hd.xml")); // Version Vidéo

            // Podtrac
            bd_sites.add(new SiteRSS("http://feeds.podtrac.com/9dPm65vdpLL1"));

            // Visual Studio Talk Show
            bd_sites.add(new SiteRSS("http://visualstudiotalkshow.libsyn.com/rss"));
        }
        catch (Exception e)
        {
            Assert.fail(e.getClass().getName());
        }
    }

    @Test
    public void TesterURLInvalide()
    {
        try
        {
            SiteRSS siteinvalide = new SiteRSS("je suis un url invalide");
            Assert.fail("Une exception aurait du être attrapé.");
        }
        catch (Exception e) {}
    }

    @Test
    public void TesterURLEspacesInvalide()
    {
        try
        {
            SiteRSS siteinvalide = new SiteRSS("https://je ne suis pas valide non plus.");
            Assert.fail("Une exception aurait du être attrapé.");
        }
        catch (Exception e) {}
    }

    @Test
    public void TesterURLErreur()
    {
        try
        {
            SiteRSS siteinvalide = new SiteRSS("http://je-nexiste-pas.com");
            Assert.fail("Une exception aurait du être attrapé.");
        }
        catch (Exception e) {}
    }

    @Test
    public void TesterURLPasRSS()
    {
        try
        {
            SiteRSS sitenorss = new SiteRSS("https://www.google.com");
            Assert.fail("Une exception aurait du être attrapé.");
        }
        catch (Exception e) {}
    }
}
