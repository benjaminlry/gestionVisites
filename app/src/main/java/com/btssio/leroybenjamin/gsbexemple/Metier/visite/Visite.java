package com.btssio.leroybenjamin.gsbexemple.Metier.visite;


import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.Medecin;
import com.btssio.leroybenjamin.gsbexemple.Metier.visiteur.Visiteur;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class Visite implements Serializable {

    private String id;
    private String date;
    private String motif;
    private String medecins_id;
    private String visiteurs_id;
    private Medecin medecin;
    private Visiteur visiteur;


    public Visite(String unId, String uneDate, String unMotif, String unMedecins_id, String unVisiteurs_id){
        this.id = unId;
        this.date = uneDate;
        this.motif = unMotif;
        this.medecins_id = unMedecins_id;
        this.visiteurs_id = unVisiteurs_id;
    }

    public HashMap<String, String> getVisite(){
        HashMap<String, String> ligne = new HashMap<String, String>();
        ligne.put("id", this.id);
        ligne.put("date", this.date);
        ligne.put("motif", this.motif);
        ligne.put("medecins_id", this.medecins_id);
        ligne.put("visiteurs_id", this.visiteurs_id);
        return ligne;
    }

    @Override
    public String toString() {
        return "Visite{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", motif='" + motif + '\'' +
                ", medecins_id='" + medecins_id + '\'' +
                ", visiteurs_id='" + visiteurs_id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getMedecinsId() {
        return medecins_id;
    }

    public void setMedecinsId(String medecins_id) {
        this.medecins_id = medecins_id;
    }

    public String getVisiteursId() {
        return visiteurs_id;
    }

    public void setVisiteursId(String visiteurs_id) {
        this.visiteurs_id = visiteurs_id;
    }
}
