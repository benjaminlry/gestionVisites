package com.btssio.leroybenjamin.gsbexemple.Medecin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.btssio.leroybenjamin.gsbexemple.Metier.VolleyHelper;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.Medecin;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.Medecin;
import com.btssio.leroybenjamin.gsbexemple.R;
import com.btssio.leroybenjamin.gsbexemple.Medecin.DetailsMedecinActivity;
import com.btssio.leroybenjamin.gsbexemple.Medecin.MedecinsActivity;

import java.util.HashMap;
import java.util.Map;

public class DetailsMedecinActivity extends AppCompatActivity {
    Medecin monMedecin;
    RequestQueue requestQueue;
    String removeMedecinUrl = "http://192.168.210.9:82/cakephp/medecins/";
    String updateMedecinUrl = "http://192.168.210.9:82/cakephp/medecins/edit/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_medecin);

        monMedecin = (Medecin) getIntent().getSerializableExtra("Medecin");

        final EditText editTextNomUpdate = (EditText) findViewById(R.id.et_detailNomMedecin);
        editTextNomUpdate.setText(monMedecin.getNom());

        final EditText editTextPrenomUpdate = (EditText) findViewById(R.id.et_detailPrenomMedecin);
        editTextPrenomUpdate.setText(monMedecin.getPrenom());

        Button buttonModifier = (Button) findViewById(R.id.btn_updateMedecin);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMedecinUrl += monMedecin.getId();
                StringRequest request = new StringRequest(Request.Method.PUT, updateMedecinUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailsMedecinActivity.this, "Ce medecin a été modifié", Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(getApplicationContext(), MedecinsActivity.class);
                startActivity(intent);
            }
        });

        Button buttonSupprimer = (Button) findViewById(R.id.btn_deleteMedecin);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        buttonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMedecinUrl += monMedecin.getId();
                StringRequest request = new StringRequest(Request.Method.DELETE, removeMedecinUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailsMedecinActivity.this, "Ce medecin a été supprimé", Toast.LENGTH_LONG).show();
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
