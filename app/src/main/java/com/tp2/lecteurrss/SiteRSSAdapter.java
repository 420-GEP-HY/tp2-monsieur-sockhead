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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.Serializable;
import java.util.List;

public class SiteRSSAdapter extends ArrayAdapter<SiteRSS> {

    List<SiteRSS> mesSites;
    Button btn;
    Context context;
    Bitmap bitmap;

    public SiteRSSAdapter(@NonNull Context context, int resource, @NonNull List<SiteRSS> objects) {
        super(context, resource, objects);
        this.mesSites = objects;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liste_sites_layout,parent,false);
        }

        TextView tv1 = convertView.findViewById(R.id.titreSite);
        TextView tv2 = convertView.findViewById(R.id.nombreNouvelles);
        ImageView iv = convertView.findViewById(R.id.imgSite);
        btn = convertView.findViewById(R.id.btnSupprimerNouvelle);

        tv1.setText(mesSites.get(position).getNomSite());
        Integer i = mesSites.get(position).getNbNouvelles();
        tv2.setText(Integer.toString(i));

        if(mesSites.get(position).getBitmap() != null)
            iv.setImageBitmap(mesSites.get(position).getBitmap());
        else
            iv.setImageResource(R.drawable.unknownicon);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesSites.remove(position);
                notifyDataSetChanged();
            }
        });
        //On click de limage
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirigerVersActivity(mesSites.get(position).getListeNouvelles());
            }
        });
        //On click du titre
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirigerVersActivity(mesSites.get(position).getListeNouvelles());
            }
        });

        return convertView;
    }

    private  void redirigerVersActivity(List<NouvellesRSS> listeNouvelles){
        Intent Activite2 = new Intent(context,ListeNouvellesActivity.class);
        Activite2.putExtra("NOUVELLES", (Serializable) listeNouvelles);
        context.startActivity(Activite2);
    }


}
