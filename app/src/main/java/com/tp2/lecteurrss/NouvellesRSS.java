package com.tp2.lecteurrss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NouvellesRSS
{
    private String Titre;
    public String getTitre() {
        return Titre;
    }
    public void setTitre(String titre) {
        Titre = titre;
    }

    private Date DatePublication;
    public Date getDatePublication() {
        return DatePublication;
    }
    public void setDatePublication(Date datePublication) {
        DatePublication = datePublication;
    }

    private String Description;
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }

    private ImageRSS image;
    public ImageRSS getImage() {
        return image;
    }
    public void setImage(ImageRSS image) {
        this.image = image;
    }

    private List<ImageRSS> ListeImage = new ArrayList<ImageRSS>();
    public List<ImageRSS> getListeImage() {
        return ListeImage;
    }
    public void setListeImage(List<ImageRSS> listeImage) {
        this.ListeImage = listeImage;
    }

    private List<VideoRSS> ListeVideo = new ArrayList<VideoRSS>();
    public List<VideoRSS> getListeVideo() {
        return ListeVideo;
    }
    public void setListeVideo(List<VideoRSS> listeVideo) {
        ListeVideo = listeVideo;
    }

    private List<AudioRSS> ListeAudio = new ArrayList<AudioRSS>();
    public List<AudioRSS> getListeAudio() {
        return ListeAudio;
    }
    public void setListeAudio(List<AudioRSS> listeAudio) {
        ListeAudio = listeAudio;
    }
}
