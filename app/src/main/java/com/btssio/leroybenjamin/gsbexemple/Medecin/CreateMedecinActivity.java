package com.btssio.leroybenjamin.gsbexemple.Medecin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.btssio.leroybenjamin.gsbexemple.R;
import com.btssio.leroybenjamin.gsbexemple.Visiteur.CreateVisiteurActivity;

import java.util.HashMap;
import java.util.Map;

public class CreateMedecinActivity extends AppCompatActivity {
    EditText editTextNom;
    EditText editTextPrenom;
    Button buttonValider;
    RequestQueue requestQueue;
    String addMedecinUrl = "http://192.168.210.9:82/cakephp/medecins/add.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_medecin);

        editTextNom = (EditText) findViewById(R.id.editText_NomMedecin);
        editTextPrenom = (EditText) findViewById(R.id.editText_PrenomMedecin);
        buttonValider = (Button) findViewById(R.id.btn_valideCreateMedecin);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, addMedecinUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateMedecinActivity.this, "Nouveau Medecin : " +
                                editTextNom.getText().toString() + " " +
                                editTextPrenom.getText().toString() +
                                " a été créer", Toast.LENGTH_LONG).show();
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
                        parameters.put("nom", editTextNom.getText().toString());
                        parameters.put("prenom", editTextPrenom.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });
    }
}
