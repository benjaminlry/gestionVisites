package com.btssio.leroybenjamin.gsbexemple.Visiteur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.btssio.leroybenjamin.gsbexemple.Metier.visiteur.Visiteur;
import com.btssio.leroybenjamin.gsbexemple.R;

import java.util.HashMap;
import java.util.Map;

public class DetailsVisiteurActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    String removeVisiteurUrl = "http://192.168.210.9:82/cakephp/visiteurs/";
    String updateVisiteurUrl = "http://192.168.210.9:82/cakephp/visiteurs/edit/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_visiteur);

        final Visiteur monVisiteur = (Visiteur) getIntent().getSerializableExtra("Visiteur");

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


    }
}
