package com.tp2.lecteurrss;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NouvellesRSS implements Serializable
{
    private String Titre;
    public String getTitre() {
        return Titre;
    }

    private String DatePublication;
    public String getDatePublication() { return DatePublication; }

    private String Description;
    public String getDescription() {
        return Description;
    }

    private String Link;
    public String getLink() {
        return Link;
    }

    private String UrlImage;
    public String getUrlImage() {
        return UrlImage;
    }
    private transient  Bitmap bitmap;
    public Bitmap getBitmap() {
        return bitmap;
    }

<<<<<<< HEAD
    private boolean EstLue;
    public boolean isEstLue() {
        return EstLue;
    }
    public void setEstLue(boolean estLue) {
        EstLue = estLue;
    }

    NouvellesRSS(String titre, String datePublication, String description, String link, String urlImage ) throws IOException {
=======
    private boolean Visionnee = false;
    public boolean aEteVisionnee() { return Visionnee; }
    public void marquerVisionnee() { Visionnee = true; }

    //NouvellesRSS(String titre, Date datePublication, String description, List<MediaRSS> listeMedia)
    NouvellesRSS(String titre, String datePublication, String description, List<MediaRSS> listeMedia)
    {
>>>>>>> 4c27e935429246b17602ee0fa1e9bb701e4dada5
        Titre = titre;
        DatePublication = datePublication;
        Description = description;
        Link = link;
        UrlImage = urlImage;
        if(UrlImage!= "")
        bitmap = GetbitmapByUrl(new URL(UrlImage));
        EstLue = true;
    }

    private Bitmap GetbitmapByUrl (URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        InputStream input = connection.getInputStream();
        return BitmapFactory.decodeStream(input);
    }


}
