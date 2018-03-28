package com.btssio.leroybenjamin.gsbexemple.Metier.medecin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.btssio.leroybenjamin.gsbexemple.Metier.medecin.Medecin;
import com.btssio.leroybenjamin.gsbexemple.R;

import java.util.ArrayList;

/**
 * Created by leroy.benjamin on 06/03/2018.
 */

public class MedecinAdapter extends ArrayAdapter<Medecin> {
    public MedecinAdapter(Context context, ArrayList<Medecin> medecins) { super(context, 0, medecins);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_medecins, parent, false);
        }

        Medecin medecinSelectionnee = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imgViewMedecin);
        imageView.setImageResource(R.drawable.avatar);

        TextView nomTextView = (TextView) listItemView.findViewById(R.id.tv_nom_medecin);
        nomTextView.setText(medecinSelectionnee.getNom());

        TextView prenomTextView = (TextView) listItemView.findViewById(R.id.tv_prenom_medecin);
        prenomTextView.setText(medecinSelectionnee.getPrenom());

        return listItemView;
    }
}
