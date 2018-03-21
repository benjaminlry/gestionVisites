/*package com.btssio.leroybenjamin.gsbexemple.Metier.visite;


import com.btssio.leroybenjamin.gsbexemple.Medecin.DetailsMedecinActivity;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.HashMap;

public class Visite implements Serializable {

    private String id;
    private DateFormat date;
    private String motif;
    private String medecins_id;
    private String visiteurs_id;


    public Visite(String unId, DateFormat uneDate, String unMotif, String unIdMedecin, String unIdVisiteur){
        this.id = unId;
        this.date = uneDate;
        this.motif = unMotif;
        this.medecins_id = unIdMedecin;
        this.visiteurs_id = unIdVisiteur;
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

    public String getMotif() {
        return motif;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
*/