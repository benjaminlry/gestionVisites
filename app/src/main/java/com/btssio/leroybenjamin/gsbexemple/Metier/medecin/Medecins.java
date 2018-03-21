package com.btssio.leroybenjamin.gsbexemple.Metier.medecin;

import com.btssio.leroybenjamin.gsbexemple.Metier.visiteur.Visiteur;

import java.util.ArrayList;
import java.util.HashMap;


public class Medecins {
    private ArrayList<Medecin> medecins;

    public ArrayList<Medecin> getMedecins() {
        return medecins;
    }

    public ArrayList<HashMap<String, String>> getVisiteursArray(){
        ArrayList<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        for(Medecin medecin : getMedecins()){
            liste.add(medecin.getMedecin());
        }
        return liste;
    }
}