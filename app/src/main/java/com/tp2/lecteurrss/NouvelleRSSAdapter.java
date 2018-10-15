package com.tp2.lecteurrss;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
        chk.setChecked(mesNouvelles.get(position).isEstLue());

        if(mesNouvelles.get(position).getBitmap() != null)
            iv.setImageBitmap(mesNouvelles.get(position).getBitmap());
        else if(mesNouvelles.get(position).getUrlImage().contains("mp3"))
            iv.setImageResource(R.drawable.musicicon);
        else if(mesNouvelles.get(position).getUrlImage().contains("mp4"))
            iv.setImageResource(R.drawable.videoicon);
        else
            iv.setImageResource(R.drawable.unknownicon);

        //On click de limage
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String url = mesNouvelles.get(position).getUrlImage();
                if(url.contains("mp3"))
                {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(mesNouvelles.get(position).getUrlImage()), "audio/mp3");
                    context.startActivity(intent);
                }
                else if(url.contains("mp4"))
                {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(mesNouvelles.get(position).getUrlImage()), "video/mp4");
                    context.startActivity(intent);
                }
            }
        });

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
        Nouvelles.setEstLue(true);
        Intent Activite3 = new Intent(context,DetailsNouvelleActivity.class);
        Activite3.putExtra("NOUVELLE", (Serializable) Nouvelles);
        context.startActivity(Activite3);
    }
}
