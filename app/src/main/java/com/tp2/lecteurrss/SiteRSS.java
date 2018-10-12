package com.tp2.lecteurrss;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
<<<<<<< HEAD

import java.io.ByteArrayOutputStream;
=======
>>>>>>> 4c27e935429246b17602ee0fa1e9bb701e4dada5
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
<<<<<<< HEAD
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
=======
>>>>>>> 4c27e935429246b17602ee0fa1e9bb701e4dada5
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SiteRSS implements Serializable
{
    private String nomSite;
    private List<NouvellesRSS> listeNouvelles = new ArrayList<NouvellesRSS>();
<<<<<<< HEAD
    private int nbNouvelles;
=======
    private MediaRSS mediaRSS = new MediaRSS();
>>>>>>> 4c27e935429246b17602ee0fa1e9bb701e4dada5

    private String urlImage;
    public String getUrlImage() {
        return urlImage;
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
        return listeNouvelles.size();
    }

    private transient Bitmap bitmap;
    public Bitmap getBitmap()
    { return bitmap;}

    public byte[] imageByteArray;

    DocumentBuilder builder;
    Document dom;

    public SiteRSS(String StringURL) throws IOException, ParserConfigurationException, SAXException
    {
        if(!(StringURL.substring(0, 7).toUpperCase() != "HTTPS://" || StringURL.substring(0, 6).toUpperCase() != "HTTP://") || StringURL.contains(" "))
            throw new MalformedURLException();

        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

<<<<<<< HEAD
        dom = builder.parse(StringURL);
        urlImage = dom.getElementsByTagName("image").item(0).getChildNodes().item(1).getTextContent();
        nomSite = dom.getElementsByTagName("title").item(0).getTextContent();
        nbNouvelles = dom.getElementsByTagName("item").getLength();
        bitmap = GetbitmapByUrl(new URL(urlImage));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        imageByteArray = stream.toByteArray();
=======
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
>>>>>>> 4c27e935429246b17602ee0fa1e9bb701e4dada5

        for(int i = 0; i < dom.getElementsByTagName("item").getLength(); i++)
        {
            Node nodeParent = dom.getElementsByTagName("item").item(i);
            String titre = "";
            String datePublication = "";
            String description = "";
            String Link = "";
            String UrlImage = "";

            for(int i2 = 0; i2 < nodeParent.getChildNodes().getLength(); i2++) {
                Node nodeChild = nodeParent.getChildNodes().item(i2);

<<<<<<< HEAD
                String nameNode = nodeChild.getNodeName();
                String textNode = nodeChild.getTextContent();
                switch (nameNode) {
                    case "title":
                        titre = textNode;
                        break;
                    case "pubDate":
                        datePublication = textNode;
                        break;
                    case "description":
                        description = textNode;
                        break;
                    case "enclosure":
                        UrlImage = nodeChild.getAttributes().getNamedItem("url").getTextContent();
                        break;
                    case "link":
                        Link = textNode;
                        break;
                    default:
=======
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
>>>>>>> 4c27e935429246b17602ee0fa1e9bb701e4dada5
            }
        }
        listeNouvelles.add(new NouvellesRSS(titre, datePublication, description,Link,UrlImage));

        }
    }
<<<<<<< HEAD



    private Bitmap GetbitmapByUrl (URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        InputStream input = connection.getInputStream();
        return BitmapFactory.decodeStream(input);
    }
=======
>>>>>>> 4c27e935429246b17602ee0fa1e9bb701e4dada5
}
