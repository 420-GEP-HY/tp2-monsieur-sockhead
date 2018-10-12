package com.tp2.lecteurrss;

import android.content.Context;
import android.content.Intent;
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

    public SiteRSSAdapter(@NonNull Context context, int resource, @NonNull List<SiteRSS> objects) {
        super(context, resource, objects);
        this.mesSites = objects;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liste_nouvelles_layout,parent,false);
        }

        TextView tv1 = convertView.findViewById(R.id.titreNouvelle);
        TextView tv2 = convertView.findViewById(R.id.nombreNouvelles);
        ImageView iv = convertView.findViewById(R.id.imgNouvelle);
        btn = convertView.findViewById(R.id.btnSupprimerNouvelle);

        btn.setTag(position);

        tv1.setText(mesSites.get(position).getNomSite());
        tv2.setText(mesSites.get(position).getNomSite());

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
