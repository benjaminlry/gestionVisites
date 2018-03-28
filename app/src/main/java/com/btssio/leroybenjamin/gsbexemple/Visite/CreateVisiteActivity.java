package com.btssio.leroybenjamin.gsbexemple.Visite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.btssio.leroybenjamin.gsbexemple.Metier.GsonRequest;
import com.btssio.leroybenjamin.gsbexemple.Metier.VolleyHelper;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.Medecin;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.MedecinAdapter;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.Medecins;
import com.btssio.leroybenjamin.gsbexemple.Metier.visiteur.Visiteur;
import com.btssio.leroybenjamin.gsbexemple.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateVisiteActivity extends AppCompatActivity {
    //EditText editTextDateVisite;
    TextView nomVisiteurVisite;
    Spinner spinnerPrenomMedecin;
    EditText editTextCommentaireVisite;
    Button buttonValiderVisite;
    RequestQueue requestQueue;
    String medecinsUrl = "http://192.168.210.9:82/cakephp/medecins.json";
    String addVisiteUrl = "http://192.168.210.9:82/cakephp/visites/add.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_visite);

        final Visiteur monVisiteur = (Visiteur) getIntent().getSerializableExtra("Visiteur");

        nomVisiteurVisite = (TextView) findViewById(R.id.textViewNomPrenomMedecin);
        nomVisiteurVisite.setText(monVisiteur.getNom() + " " + monVisiteur.getPrenom());

        final GsonRequest gsonRequest = new GsonRequest(medecinsUrl, Medecins.class, null, new Response.Listener<Medecins>() {
            @Override
            public void onResponse(Medecins medecins) {
                ArrayList<Medecin> liste = medecins.getMedecins();

                MedecinAdapter medecinAdapter = new MedecinAdapter(getApplicationContext(), liste);
                medecinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPrenomMedecin.setAdapter(medecinAdapter); // Envoie de tous les médecins dans un spinner
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError != null) Log.e("VisitesActivity", volleyError.getMessage());
            }
        });
        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest); // Envoie la requête

        Button buttonRetourVisiteCreate = (Button) findViewById(R.id.btn_returnVisiteCreate);
        buttonRetourVisiteCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spinnerPrenomMedecin = (Spinner) findViewById(R.id.spinnerPrenomMedecin);
        editTextCommentaireVisite = (EditText) findViewById(R.id.editText_CommentaireVisite);
        buttonValiderVisite = (Button) findViewById(R.id.btn_valideVisiteCreate);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        buttonValiderVisite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, addVisiteUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateVisiteActivity.this, "Nouvelle visite : " +
                                spinnerPrenomMedecin.getSelectedItem().toString() +
                                " a été créer", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    Date date = Calendar.getInstance().getTime();
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String today = formatter.format(date);

                    Medecin monMedecin = (Medecin) spinnerPrenomMedecin.getSelectedItem();
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String,String>();
                        parameters.put("date", today);
                        parameters.put("motif", editTextCommentaireVisite.getText().toString());
                        parameters.put("medecins_id", monMedecin.getId());
                        parameters.put("visiteurs_id", monVisiteur.getId());
                        return parameters; // Validation d'une visite
                    }
                };
                requestQueue.add(request);
            }
        });


    }
}
