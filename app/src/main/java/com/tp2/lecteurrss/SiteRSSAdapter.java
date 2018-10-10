package com.tp2.lecteurrss;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SiteRSSAdapter extends ArrayAdapter<SiteRSS> {

    List<SiteRSS> mesSites;
    Button btn;

    public SiteRSSAdapter(@NonNull Context context, int resource, @NonNull List<SiteRSS> objects) {
        super(context, resource, objects);
        this.mesSites = objects;
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

        return convertView;
    }
}
