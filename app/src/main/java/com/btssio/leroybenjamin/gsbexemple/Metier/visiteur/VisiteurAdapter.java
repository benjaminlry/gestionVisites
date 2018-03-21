package com.btssio.leroybenjamin.gsbexemple.Metier.visiteur;

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

public class VisiteurAdapter extends ArrayAdapter<Visiteur> {
    public VisiteurAdapter(Context context, ArrayList<Visiteur> visiteurs) { super(context, 0, visiteurs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_visiteurs, parent, false);
        }

        Visiteur visiteurSelectionnee = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imgViewVisiteur);
        imageView.setImageResource(R.drawable.avatar);

        TextView idTextView = (TextView) listItemView.findViewById(R.id.tv_id_visiteur);
        idTextView.setText(visiteurSelectionnee.getId());

        TextView nomTextView = (TextView) listItemView.findViewById(R.id.tv_nom_visiteur);
        nomTextView.setText(visiteurSelectionnee.getNom());

        TextView prenomTextView = (TextView) listItemView.findViewById(R.id.tv_prenom_visiteur);
        prenomTextView.setText(visiteurSelectionnee.getPrenom());

        return listItemView;
    }
}
