package com.tp2.lecteurrss;

import org.junit.Assert;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

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
        }
        catch (IOException e)
        {
            Assert.fail(e.getMessage());
        }
        catch (ParserConfigurationException e)
        {
            Assert.fail(e.getMessage());
        }
        catch (SAXException e)
        {
            Assert.fail(e.getMessage());
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
        catch (IOException e) {}
        catch (ParserConfigurationException e) {}
        catch (SAXException e) {}
    }

    @Test
    public void TesterURLEspacesInvalide()
    {
        try
        {
            SiteRSS siteinvalide = new SiteRSS("https://je ne suis pas valide non plus.");
            Assert.fail("Une exception aurait du être attrapé.");
        }
        catch (IOException e) {}
        catch (ParserConfigurationException e) {}
        catch (SAXException e) {}
    }

    @Test
    public void TesterURLErreur()
    {
        try
        {
            SiteRSS siteinvalide = new SiteRSS("http://je-nexiste-pas.com");
            Assert.fail("Une exception aurait du être attrapé.");
        }
        catch (IOException e) {}
        catch (ParserConfigurationException e) {}
        catch (SAXException e) {}
    }

    @Test
    public void TesterURLPasRSS()
    {
        try
        {
            SiteRSS sitenorss = new SiteRSS("https://www.google.com");
            Assert.fail("Une exception aurait du être attrapé.");
        }
        catch (IOException e) {}
        catch (ParserConfigurationException e) {}
        catch (SAXException e) {}
    }
}
