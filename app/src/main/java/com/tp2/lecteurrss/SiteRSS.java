package com.tp2.lecteurrss;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SiteRSS
{
    private String nomSite;
    private List<NouvellesRSS> listeNouvelles = new ArrayList<NouvellesRSS>();
    private int nbNouvelles;
    private String image;

    public SiteRSS(String URL) throws IOException, ParserConfigurationException, SAXException
    {
        DocumentBuilder builder;
        Document dom;

        if(!(URL.substring(0, 7).toUpperCase() != "HTTPS://" || URL.substring(0, 6).toUpperCase() != "HTTP://") || URL.contains(" "))
            throw new MalformedURLException();

        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        dom = builder.parse(URL);
        nomSite = dom.getElementsByTagName("title").item(0).getTextContent();
    }

    public String getNomSite()
    {
        return nomSite;
    }

    public List<NouvellesRSS> getListeNouvelles()
    {
        return listeNouvelles;
    }

    public int getNbNouvelles()
    {
        return nbNouvelles;
    }

    public String getImageURL()
    {
        return image;
    }
}
