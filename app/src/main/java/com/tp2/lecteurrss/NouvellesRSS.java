package com.tp2.lecteurrss;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
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
    public void setBitmap(Bitmap bmp){
        bitmap = bmp;
    }

    private boolean EstLue;
    public boolean isEstLue() {
        return EstLue;
    }
    public void setEstLue(boolean estLue) {
        EstLue = estLue;
    }

    private byte[] imageByteArray;
    public void setImageByArray(byte[] array)
    {
        imageByteArray = array;
    }
    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    NouvellesRSS(String titre, String datePublication, String description, String link, String urlImage ) throws IOException
    {
        Titre = titre;
        DatePublication = datePublication;
        Description = description;
        Link = link;
        UrlImage = urlImage;

        EstLue = false;
    }

    public Bitmap GetbitmapByUrl (URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        InputStream input = connection.getInputStream();
        return BitmapFactory.decodeStream(input);
    }
    public Bitmap BimapArray(byte[] arrayImage){

        return BitmapFactory.decodeByteArray(arrayImage, 0, arrayImage.length);
    }

}
