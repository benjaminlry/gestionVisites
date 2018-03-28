package com.btssio.leroybenjamin.gsbexemple.Metier.visite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.btssio.leroybenjamin.gsbexemple.R;

import java.util.ArrayList;

/**
 * Created by leroy.benjamin on 06/03/2018.
 */

public class VisiteAdapter extends ArrayAdapter<Visite> {
    public VisiteAdapter(Context context, ArrayList<Visite> visites) { super(context, 0, visites);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_visites, parent, false);
        }

        Visite visiteSelectionnee = getItem(position);

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.tv_date_visite);
        dateTextView.setText(visiteSelectionnee.getDate());

        TextView idTextView = (TextView) listItemView.findViewById(R.id.tv_id_visite);
        idTextView.setText(visiteSelectionnee.getId());

        TextView medecinTextView = (TextView) listItemView.findViewById(R.id.tv_medecin_visite);
        medecinTextView.setText(visiteSelectionnee.getMedecin().getPrenom() + " " + visiteSelectionnee.getMedecin().getNom());


        return listItemView;
    }
}
