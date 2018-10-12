package com.tp2.lecteurrss;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.Serializable;
import java.util.List;

public class NouvelleRSSAdapter extends ArrayAdapter<NouvellesRSS> {

    List<NouvellesRSS> mesNouvelles;
    Context context;
    Bitmap bitmap;

    public NouvelleRSSAdapter(@NonNull Context context, int resource, @NonNull List<NouvellesRSS> objects) {
        super(context, resource, objects);
        this.mesNouvelles = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liste_nouvelles_layout,parent,false);
        }

        TextView tv1 = convertView.findViewById(R.id.titreNouvelle);
        ImageView iv = convertView.findViewById(R.id.imgNouvelle);
        CheckBox chk = convertView.findViewById(R.id.checkboxLue);

        tv1.setText(mesNouvelles.get(position).getTitre());
        //iv.setImageBitmap(mesNouvelles.get(position).getBitmap());
        chk.setChecked(mesNouvelles.get(position).isEstLue());

        //On click de limage
        //iv.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View v) {
              //  redirigerVersActivity(mesNouvelles.get(position));
            //}
        //});
        //On click du titre
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirigerVersActivity(mesNouvelles.get(position));
            }
        });

        return convertView;
    }



    private  void redirigerVersActivity(NouvellesRSS Nouvelles){
        Intent Activite3 = new Intent(context,DetailsNouvelleActivity.class);
        Activite3.putExtra("NOUVELLE", (Serializable) Nouvelles);
        context.startActivity(Activite3);
    }
}
