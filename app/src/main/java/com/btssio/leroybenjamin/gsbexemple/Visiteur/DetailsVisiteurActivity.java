package com.btssio.leroybenjamin.gsbexemple.Visiteur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.btssio.leroybenjamin.gsbexemple.Metier.GsonRequest;
import com.btssio.leroybenjamin.gsbexemple.Metier.VolleyHelper;
import com.btssio.leroybenjamin.gsbexemple.Metier.visite.Visite;
import com.btssio.leroybenjamin.gsbexemple.Metier.visite.VisiteAdapter;
import com.btssio.leroybenjamin.gsbexemple.Metier.visite.Visites;
import com.btssio.leroybenjamin.gsbexemple.Metier.visiteur.Visiteur;
import com.btssio.leroybenjamin.gsbexemple.Metier.visiteur.VisiteurAdapter;
import com.btssio.leroybenjamin.gsbexemple.Metier.visiteur.Visiteurs;
import com.btssio.leroybenjamin.gsbexemple.R;
import com.btssio.leroybenjamin.gsbexemple.Visite.CreateVisiteActivity;
import com.btssio.leroybenjamin.gsbexemple.Visite.DetailsVisiteActivity;
import com.btssio.leroybenjamin.gsbexemple.Visite.VisitesActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailsVisiteurActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    String removeVisiteurUrl = "http://192.168.210.9:82/cakephp/visiteurs/";
    String updateVisiteurUrl = "http://192.168.210.9:82/cakephp/visiteurs/edit/";
    String visitesUrl = "http://192.168.210.9:82/cakephp/visites.json";
    ListView lvVisites;
    Button btnActualiseVisites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_visiteur);

        final Visiteur monVisiteur = (Visiteur) getIntent().getSerializableExtra("Visite");

        final EditText editTextNomUpdate = (EditText) findViewById(R.id.et_detailNom);
        editTextNomUpdate.setText(monVisiteur.getNom());

        final EditText editTextPrenomUpdate = (EditText) findViewById(R.id.et_detailPrenom);
        editTextPrenomUpdate.setText(monVisiteur.getPrenom());

        Button buttonModifier = (Button) findViewById(R.id.btn_updateVisiteur);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateVisiteurUrl += monVisiteur.getId();
                StringRequest request = new StringRequest(Request.Method.PUT, updateVisiteurUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailsVisiteurActivity.this, "Ce visiteur a été modifié", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String,String>();
                        parameters.put("nom", editTextNomUpdate.getText().toString());
                        parameters.put("prenom", editTextPrenomUpdate.getText().toString());
                        return parameters;
                    }
                };
                requestQueue.add(request);
                Intent intent = new Intent(getApplicationContext(), VisiteursActivity.class);
                startActivity(intent);
            }
        });

        Button buttonSupprimer = (Button) findViewById(R.id.btn_deleteVisiteur);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        buttonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeVisiteurUrl += monVisiteur.getId();
                StringRequest request = new StringRequest(Request.Method.DELETE, removeVisiteurUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailsVisiteurActivity.this, "Ce visiteur a été supprimé", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String,String>();

                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });

        lvVisites = (ListView) findViewById(R.id.lv_visites);
        lvVisites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Visite maVisite;
                maVisite = (Visite) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(DetailsVisiteurActivity.this, DetailsVisiteActivity.class);
                intent.putExtra("Visite", (Serializable)maVisite);
                startActivity(intent);
            }
        });
        Button btnActualiseVisites = (Button) findViewById(R.id.btn_actualiseVisites);
        btnActualiseVisites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final GsonRequest gsonRequest = new GsonRequest(visitesUrl, Visites.class, null, new Response.Listener<Visites>() {
                    @Override
                    public void onResponse(Visites visites) {

                        ArrayList<Visite> v = new ArrayList<Visite>();
                        for(Visite element : visites.getVisites()){
                            if( element.getVisiteursId().equals(monVisiteur.getId())){
                                v.add(element);
                            }
                        }
                        VisiteAdapter adapterVisite = new VisiteAdapter(getApplicationContext(), v);
                        lvVisites.setAdapter(adapterVisite);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError != null)
                            Log.e("VisitesActivity", volleyError.getMessage());
                    }
                });

                VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
            }
        });

        btnActualiseVisites.callOnClick();

        Button buttonRetourVisiteursDetails = (Button) findViewById(R.id.btn_returnVisiteursDetails);
        buttonRetourVisiteursDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buttonCreateVisite  = (Button) findViewById(R.id.btn_createVisite);
        buttonCreateVisite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateVisiteActivity.class);
                intent.putExtra("Visiteur", (Serializable)monVisiteur);
                startActivity(intent);
            }
        });
    }
}
