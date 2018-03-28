package com.btssio.leroybenjamin.gsbexemple.Visite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.Medecin;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.MedecinAdapter;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.Medecins;
import com.btssio.leroybenjamin.gsbexemple.Metier.visite.Visite;
import com.btssio.leroybenjamin.gsbexemple.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailsVisiteActivity extends AppCompatActivity {
    Button buttonDeleteVisite;
    RequestQueue requestQueue;
    String deleteVisiteUrl = "http://192.168.210.9:82/cakephp/visites/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_visite);

        final Visite maVisite = (Visite) getIntent().getSerializableExtra("Visite");

        final TextView textViewDate = (TextView) findViewById(R.id.dateVisiteDetail);
        textViewDate.setText(maVisite.getDate());

        final TextView textViewMedecin = (TextView) findViewById(R.id.nomMedecinVisiteDetail);
        textViewMedecin.setText(maVisite.getMedecin().getPrenom() + " " + maVisite.getMedecin().getNom());

        final TextView textViewMotif = (TextView) findViewById(R.id.motifVisiteDetail);
        textViewMotif.setText(maVisite.getMotif());

        Button buttonRetourVisites = (Button) findViewById(R.id.btn_returnVisites);
        buttonRetourVisites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonDeleteVisite = (Button) findViewById(R.id.btn_supprimerVisite);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        buttonDeleteVisite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVisiteUrl += maVisite.getId();
                StringRequest request = new StringRequest(Request.Method.DELETE, deleteVisiteUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailsVisiteActivity.this, "Cette visite a été supprimé", Toast.LENGTH_LONG).show();
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
    }
}
