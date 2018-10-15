package com.tp2.lecteurrss;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SiteRSS implements Serializable
{
    private String nomSite;
    public String getNomSite()
    {
        return nomSite;
    }

    private List<NouvellesRSS> listeNouvelles = new ArrayList<NouvellesRSS>();
    public List<NouvellesRSS> getListeNouvelles()
    {
        return listeNouvelles;
    }
    public int getNbNouvelles()
    {
        return listeNouvelles.size();
    }

    private String urlImage;
    public String getUrlImage() {
        return urlImage;
    }

    private transient Bitmap bitmap;
    public void setBitpmap(Bitmap bmp)
    {
        bitmap = bmp;
    }
    public Bitmap getBitmap() { return bitmap;}

    private byte[] imageByteArray;
    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    public SiteRSS(String StringURL, boolean test) throws IOException, ParserConfigurationException, SAXException
    {
        DocumentBuilder builder;
        Document dom;

        if(!(StringURL.substring(0, 7).toUpperCase() != "HTTPS://" || StringURL.substring(0, 6).toUpperCase() != "HTTP://") || StringURL.contains(" "))
            throw new MalformedURLException();

        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        dom = builder.parse(StringURL);

        nomSite = dom.getElementsByTagName("title").item(0).getTextContent();

        if(dom.getElementsByTagName("image").getLength() > 0)
        {
            int nbNodes = dom.getElementsByTagName("image").item(0).getChildNodes().getLength();
            for(int i = 0; i < nbNodes; i++)
            {
                String tmpImage = dom.getElementsByTagName("image").item(0).getChildNodes().item(i).getTextContent();

                if(verifImage(tmpImage))
                {
                    urlImage = tmpImage;
                    if(!test)
                        bitmap = GetbitmapByUrl(new URL(urlImage));
                    break;
                }
            }
        }

        if(!test && urlImage != null)
        {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imageByteArray = stream.toByteArray();
        }

        for(int i = 0; i < dom.getElementsByTagName("item").getLength(); i++)
        {
            Node nodeParent = dom.getElementsByTagName("item").item(i);
            String titre = "";
            String datePublication = "";
            String description = "";
            String Link = "";
            String UrlImage = "";

            for(int i2 = 0; i2 < nodeParent.getChildNodes().getLength(); i2++)
            {
                Node nodeChild = nodeParent.getChildNodes().item(i2);

                switch (nodeChild.getNodeName())
                {
                    case "title":
                        titre = nodeChild.getTextContent();
                        break;
                    case "pubDate":
                    case "dc:date":
                        datePublication = nodeChild.getTextContent();
                        break;
                    case "description":
                        description = nodeChild.getTextContent();
                        break;
                    case "enclosure":
                    case "media:thumbnail":
                        if(UrlImage.isEmpty())
                            UrlImage = nodeChild.getAttributes().getNamedItem("url").getTextContent();
                        break;
                    case "itunes:image":
                        if(UrlImage.isEmpty())
                            UrlImage = nodeChild.getAttributes().getNamedItem("href").getTextContent();
                        break;
                    case "link":
                        Link = nodeChild.getTextContent();
                        break;
                }
            }
        listeNouvelles.add(new NouvellesRSS(titre, datePublication, description, Link, UrlImage));
        }
    }

    private Bitmap GetbitmapByUrl (URL url) throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        InputStream input = connection.getInputStream();
        return BitmapFactory.decodeStream(input);
    }

    public Bitmap BimapArray( byte[] arrayImage)
    {
        if(arrayImage != null)
            return BitmapFactory.decodeByteArray(arrayImage, 0, arrayImage.length);
        else
            return null;
    }

    private boolean verifImage(String imageURL)
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
