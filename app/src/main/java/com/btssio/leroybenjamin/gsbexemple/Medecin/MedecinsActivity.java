package com.btssio.leroybenjamin.gsbexemple.Medecin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.btssio.leroybenjamin.gsbexemple.Metier.GsonRequest;
import com.btssio.leroybenjamin.gsbexemple.Metier.VolleyHelper;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.Medecin;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.MedecinAdapter;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.Medecin;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.MedecinAdapter;
import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.Medecins;
import com.btssio.leroybenjamin.gsbexemple.R;
import com.btssio.leroybenjamin.gsbexemple.Medecin.CreateMedecinActivity;
import com.btssio.leroybenjamin.gsbexemple.Medecin.DetailsMedecinActivity;
import com.btssio.leroybenjamin.gsbexemple.Medecin.MedecinsActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class MedecinsActivity extends Activity {
    RequestQueue requestQueue;
    //URL de l'API REST (envoie des données au format JSON)
    String medecinsUrl = "http://192.168.210.9:82/cakephp/medecins.json";
    ListView listViewMedecins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecins);

        listViewMedecins = (ListView) findViewById(R.id.lv_medecins);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Button buttonCreateMedecin  = (Button) findViewById(R.id.btn_createMedecin);
        buttonCreateMedecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateMedecinActivity.class);
                startActivity(intent);
            }
        });

        Button buttonActualiseMedecin = (Button) findViewById(R.id.btn_actualiseMedecin);
        buttonActualiseMedecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final GsonRequest gsonRequest = new GsonRequest(medecinsUrl, Medecins.class, null, new Response.Listener<Medecins>() {
                    @Override
                    public void onResponse(Medecins medecins) {
                        ArrayList<Medecin> liste = medecins.getMedecins();
                        MedecinAdapter adapterMedecin = new MedecinAdapter(getApplicationContext(), liste);

                        ListView listViewMedecins = (ListView) findViewById(R.id.lv_medecins);
                        listViewMedecins.setAdapter(adapterMedecin);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError != null) Log.e("MedecinsActivity", volleyError.getMessage());
                    }
                });
                VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
            }
        });

        listViewMedecins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Medecin monMedecin;
                monMedecin = (Medecin) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MedecinsActivity.this, DetailsMedecinActivity.class);
                intent.putExtra("Medecin", (Serializable)monMedecin);
                startActivity(intent);
            }
        });

        buttonActualiseMedecin.callOnClick();
    }
}
