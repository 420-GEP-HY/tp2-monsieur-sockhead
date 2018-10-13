package com.tp2.lecteurrss;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DetailsNouvelleActivity extends AppCompatActivity {

    TextView textViewTitre;
    TextView textViewDateParution;
    TextView textViewDescription;
    TextView textViewLink;
    ImageView imageNouvelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final NouvellesRSS nouvelle;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_nouvelle);
        nouvelle = (NouvellesRSS) getIntent().getSerializableExtra("NOUVELLE");

        textViewTitre = this.findViewById(R.id.titreDetailsNouvelle);
        textViewTitre.setText(nouvelle.getTitre());
        textViewDateParution = this.findViewById(R.id.dateParution);
        textViewDateParution.setText(nouvelle.getDatePublication());
        textViewDescription = this.findViewById(R.id.descriptionNouvelle);
        textViewDescription.setText(Html.fromHtml(nouvelle.getDescription()));
        textViewLink = this.findViewById(R.id.link);
        textViewLink.setText(nouvelle.getLink());
        imageNouvelle  = findViewById(R.id.imgDetailsNouvelle);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    nouvelle.setBitmap(GetbitmapByUrl(new URL(nouvelle.getUrlImage())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(nouvelle.getBitmap() != null)
                {
                    DetailsNouvelleActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageNouvelle.setImageBitmap(nouvelle.getBitmap());
                        }
                    });
                }
            }
        }).start();

    }

    private Bitmap GetbitmapByUrl (URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        InputStream input = connection.getInputStream();
        return BitmapFactory.decodeStream(input);
    }
}
