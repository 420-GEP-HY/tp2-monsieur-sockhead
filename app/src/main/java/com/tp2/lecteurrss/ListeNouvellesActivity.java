package com.tp2.lecteurrss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class ListeNouvellesActivity extends AppCompatActivity {

    List<NouvellesRSS> listeNouvelles = new ArrayList<NouvellesRSS>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_nouvelles);

        //Pour obtenir la liste des nouvelles
        listeNouvelles= (List<NouvellesRSS>) getIntent().getSerializableExtra("NOUVELLES");
    }
}
