package com.btssio.leroybenjamin.gsbexemple.Visiteur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.btssio.leroybenjamin.gsbexemple.Metier.GsonRequest;
import com.btssio.leroybenjamin.gsbexemple.Metier.visiteur.Visiteur;
import com.btssio.leroybenjamin.gsbexemple.Metier.visiteur.Visiteurs;
import com.btssio.leroybenjamin.gsbexemple.Metier.VolleyHelper;
import com.btssio.leroybenjamin.gsbexemple.Metier.visiteur.VisiteurAdapter;
import com.btssio.leroybenjamin.gsbexemple.R;

import java.io.Serializable;
import java.util.ArrayList;

public class VisiteursActivity extends Activity {
    RequestQueue requestQueue;
    //URL de l'API REST (envoie des données au format JSON)
    String visiteursUrl = "http://192.168.210.9:82/cakephp/visiteurs.json";
    ListView listViewVisiteurs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiteurs);

        listViewVisiteurs = (ListView) findViewById(R.id.lv_visiteurs);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Button buttonCreateVisiteur  = (Button) findViewById(R.id.btn_createVisiteur);
        buttonCreateVisiteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateVisiteurActivity.class);
                startActivity(intent);
            }
        });

        Button buttonActualiseVisiteur = (Button) findViewById(R.id.btn_actualiseVisiteur);
        buttonActualiseVisiteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final GsonRequest gsonRequest = new GsonRequest(visiteursUrl, Visiteurs.class, null, new Response.Listener<Visiteurs>() {
                    @Override
                    public void onResponse(Visiteurs visiteurs) {
                        ArrayList<Visiteur> liste = visiteurs.getVisiteurs();
                        VisiteurAdapter adapterVisiteur = new VisiteurAdapter(getApplicationContext(), liste);

                        ListView listViewVisiteurs = (ListView) findViewById(R.id.lv_visiteurs);
                        listViewVisiteurs.setAdapter(adapterVisiteur);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError != null) Log.e("VisiteursActivity", volleyError.getMessage());
                    }
                });
                VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
            }
        });

        listViewVisiteurs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Visiteur monVisiteur;
                monVisiteur = (Visiteur) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(VisiteursActivity.this, DetailsVisiteurActivity.class);
                intent.putExtra("Visiteur", (Serializable)monVisiteur);
                startActivity(intent);
            }
        });

        buttonActualiseVisiteur.callOnClick();
    }
}
