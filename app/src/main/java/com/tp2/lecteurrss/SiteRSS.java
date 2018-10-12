package com.tp2.lecteurrss;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SiteRSS implements Serializable
{
    private String nomSite;
    private List<NouvellesRSS> listeNouvelles = new ArrayList<NouvellesRSS>();
    private int nbNouvelles;

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
        return nbNouvelles;
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

        dom = builder.parse(StringURL);
        urlImage = dom.getElementsByTagName("image").item(0).getChildNodes().item(1).getTextContent();
        nomSite = dom.getElementsByTagName("title").item(0).getTextContent();
        nbNouvelles = dom.getElementsByTagName("item").getLength();
        bitmap = GetbitmapByUrl(new URL(urlImage));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        imageByteArray = stream.toByteArray();

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
            }
        }
        listeNouvelles.add(new NouvellesRSS(titre, datePublication, description,Link,UrlImage));

        }
    }



    private Bitmap GetbitmapByUrl (URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        InputStream input = connection.getInputStream();
        return BitmapFactory.decodeStream(input);
    }
}
