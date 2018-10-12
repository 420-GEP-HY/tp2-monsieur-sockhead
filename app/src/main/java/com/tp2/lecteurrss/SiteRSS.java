package com.tp2.lecteurrss;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SiteRSS implements Serializable
{
    private String nomSite;
    private List<NouvellesRSS> listeNouvelles = new ArrayList<NouvellesRSS>();
    private MediaRSS mediaRSS = new MediaRSS();

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
        return listeNouvelles.size();
    }

    public String getImageURL()
    {
        return mediaRSS.getUrl();
    }

    public SiteRSS(String URL) throws IOException, ParserConfigurationException, SAXException
    {
        DocumentBuilder builder;
        Document dom;

        if(!(URL.substring(0, 7).toUpperCase() != "HTTPS://" || URL.substring(0, 6).toUpperCase() != "HTTP://") || URL.contains(" "))
            throw new MalformedURLException();

        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        dom = builder.parse(URL);
        nomSite = dom.getElementsByTagName("title").item(0).getTextContent();

        if(dom.getElementsByTagName("image").getLength() > 0)
        {
            int nbNodes = dom.getElementsByTagName("image").item(0).getChildNodes().getLength();
            for (int i = 0; i < nbNodes; i++)
            {
                String imageurl = dom.getElementsByTagName("image").item(0).getChildNodes().item(i).getTextContent();

                if (mediaRSS.verifImage(imageurl))
                {
                    mediaRSS.setUrl(imageurl);
                    if(imageurl.toLowerCase().contains(".gif") || imageurl.toLowerCase().contains(".png") || imageurl.toLowerCase().contains(".jpg"))
                        mediaRSS.setType("image/jpeg");
                    break;
                }
            }
        }

        for(int i = 0; i < dom.getElementsByTagName("item").getLength(); i++)
        {
            Node nodeParent = dom.getElementsByTagName("item").item(i);
            String titre = "";
            //Date datePublication = null;
            String datePublication = "";
            String description = "";
            List<MediaRSS> listeMedia = new ArrayList<MediaRSS>();

            for(int i2 = 0; i2 < nodeParent.getChildNodes().getLength(); i2++)
            {
                Node nodeChild = nodeParent.getChildNodes().item(i2);

                if(nodeChild.getNodeName() == "title")
                    titre = nodeChild.getTextContent();
                else if(nodeChild.getNodeName() == "pubDate" || nodeChild.getNodeName() == "dc:date")
                {
                    // La date échoue pour parser le String a chaque fois, même si c'est hardcodé.
                    // On pourra décommenter le code lorsqu'on aura résolu ce problème.
                    //try
                    //{
                        datePublication = nodeChild.getTextContent();
                        //datePublication = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").parse(nodeChild.getTextContent());
                    //}
                    //catch (ParseException e)
                    //{
                    //    datePublication = null;
                    //}
                }
                else if(nodeChild.getNodeName() == "description")
                    description = nodeChild.getTextContent();
                else if(nodeChild.getNodeName() == "enclosure")
                {
                    Node nodeChildChildURL = nodeChild.getAttributes().getNamedItem("url");
                    Node nodeChildChildType = nodeChild.getAttributes().getNamedItem("type");

                    listeMedia.add(new MediaRSS(nodeChildChildURL.getTextContent(), nodeChildChildType.getTextContent()));
                }
                else if(nodeChild.getNodeName() == "media:thumbnail")
                {
                    Node nodeChildChildURL = nodeChild.getAttributes().getNamedItem("url");
                    String imageurl = nodeChildChildURL.getTextContent();
                    String imagetype = null;

                    if (mediaRSS.verifImage(imageurl))
                        if(imageurl.toLowerCase().contains(".gif") || imageurl.toLowerCase().contains(".png") || imageurl.toLowerCase().contains(".jpg"))
                            imagetype = "image/jpeg";

                    listeMedia.add(new MediaRSS(imageurl, imagetype));
                }
                else if(nodeChild.getNodeName() == "itunes:image")
                {
                    Node nodeChildChildhref = nodeChild.getAttributes().getNamedItem("href");
                    String imageurl = nodeChildChildhref.getTextContent();
                    String imagetype = null;

                    if (mediaRSS.verifImage(imageurl))
                        if(imageurl.toLowerCase().contains(".gif") || imageurl.toLowerCase().contains(".png") || imageurl.toLowerCase().contains(".jpg"))
                            imagetype = "image/jpeg";

                    listeMedia.add(new MediaRSS(imageurl, imagetype));
                }
            }
            listeNouvelles.add(new NouvellesRSS(titre, datePublication, description, listeMedia));
        }
    }
}
