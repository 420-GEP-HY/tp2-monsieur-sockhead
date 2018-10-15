package com.tp2.lecteurrss;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class ListeNouvellesActivity extends AppCompatActivity {

    List<NouvellesRSS> listeNouvelles = new ArrayList<NouvellesRSS>();
    ListView listeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_nouvelles);
        listeView = findViewById(R.id.lstViewNouvelle);

        //Pour obtenir la liste des nouvelles
        listeNouvelles = (List<NouvellesRSS>) getIntent().getSerializableExtra("NOUVELLES");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    for(NouvellesRSS nouvelle: listeNouvelles){
                        if(!nouvelle.getUrlImage().equals(""))
                        {
                            if(!nouvelle.getUrlImage().contains(".mp3") && !nouvelle.getUrlImage().contains(".mp4"))
                            {
                                nouvelle.setBitmap(nouvelle.GetbitmapByUrl(new URL(nouvelle.getUrlImage())));
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                nouvelle.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
                                nouvelle.setImageByArray(stream.toByteArray());
                                nouvelle.setBitmap(nouvelle.BimapArray(nouvelle.getImageByteArray()));
                            }
                        }
                    }
                    ListeNouvellesActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateAdapter();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void updateAdapter(){
        ArrayAdapter<NouvellesRSS> aa = new NouvelleRSSAdapter(this,0,listeNouvelles);
        listeView.setAdapter(aa);
    }


}
