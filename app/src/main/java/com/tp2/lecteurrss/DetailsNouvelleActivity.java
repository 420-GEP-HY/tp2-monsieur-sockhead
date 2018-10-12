package com.tp2.lecteurrss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DetailsNouvelleActivity extends AppCompatActivity {

    TextView textViewTitre;
    TextView textViewDateParution;
    TextView textViewDescription;
    TextView textViewLink;
    ImageView imageNouvelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NouvellesRSS nouvelle;
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

    }
}
