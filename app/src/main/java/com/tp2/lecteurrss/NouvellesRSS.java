package com.tp2.lecteurrss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NouvellesRSS implements Serializable
{
    private String Titre;
    public String getTitre() {
        return Titre;
    }

    /*
    private Date DatePublication;
    public Date getDatePublication() {
        return DatePublication;
    }
    */

    private String DatePublication;
    public String getDatePublication() { return DatePublication; }

    private String Description;
    public String getDescription() {
        return Description;
    }

    private List<MediaRSS> ListeMedia = new ArrayList<MediaRSS>();
    public List<MediaRSS> getListeMedia() {
        return ListeMedia;
    }

    private boolean Visionnee = false;
    public boolean aEteVisionnee() { return Visionnee; }
    public void marquerVisionnee() { Visionnee = true; }

    //NouvellesRSS(String titre, Date datePublication, String description, List<MediaRSS> listeMedia)
    NouvellesRSS(String titre, String datePublication, String description, List<MediaRSS> listeMedia)
    {
        Titre = titre;
        DatePublication = datePublication;
        Description = description;
        ListeMedia = listeMedia;
    }
    NouvellesRSS(String titre){
        Titre = titre;
    }
}
