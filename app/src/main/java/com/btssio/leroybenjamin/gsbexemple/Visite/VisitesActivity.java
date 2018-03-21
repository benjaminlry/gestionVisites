package com.btssio.leroybenjamin.gsbexemple.Visite;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.btssio.leroybenjamin.gsbexemple.Metier.visite.Visite;
import com.btssio.leroybenjamin.gsbexemple.R;

import java.io.Serializable;

public class VisitesActivity extends Activity {
    RequestQueue requestQueue;
    String visitesUrl = "http://192.168.210.9:82/cakephp/visites.json";
    ListView listViewVisites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visites);

        listViewVisites = (ListView) findViewById(R.id.lv_visites);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        listViewVisites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Visite maVisite;
                maVisite = (Visite) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(VisitesActivity.this, DetailsVisiteActivity.class);
                intent.putExtra("Visite", (Serializable) maVisite);
                startActivity(intent);
            }
        });
    }
}
