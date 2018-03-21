package com.btssio.leroybenjamin.gsbexemple.Metier.visiteur;

import java.util.ArrayList;
import java.util.HashMap;


public class Visiteurs {
    private ArrayList<Visiteur> visiteurs;

    public ArrayList<Visiteur> getVisiteurs() {
        return visiteurs;
    }

    public ArrayList<HashMap<String, String>> getVisiteursArray(){
        ArrayList<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        for(Visiteur visiteur : getVisiteurs()){
            liste.add(visiteur.getVisiteur());
        }
        return liste;
    }
}