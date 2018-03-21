package com.btssio.leroybenjamin.gsbexemple.Metier.visite;

import java.util.ArrayList;
import java.util.HashMap;


public class Visites {
    private ArrayList<Visite> visites;

    public ArrayList<Visite> getVisites() {
        return visites;
    }

    public ArrayList<HashMap<String, String>> getVisitesArray(){
        ArrayList<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        for(Visite visite : getVisites()){
            liste.add(visite.getVisite());
        }
        return liste;
    }
}