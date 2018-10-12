package com.tp2.lecteurrss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListeNouvellesActivity extends AppCompatActivity {

    List<NouvellesRSS> listeNouvelles = new ArrayList<NouvellesRSS>();
    ListView listeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_nouvelles);

        listeView = findViewById(R.id.lstViewNouvelle);

        //Pour obtenir la liste des nouvelles
        listeNouvelles= (List<NouvellesRSS>) getIntent().getSerializableExtra("NOUVELLES");
        updateAdapter();
    }

    private void updateAdapter(){
        ArrayAdapter<NouvellesRSS> aa = new NouvelleRSSAdapter(this,0,listeNouvelles);
        listeView.setAdapter(aa);
    }
}
