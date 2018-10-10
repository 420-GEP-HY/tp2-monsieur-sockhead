package com.tp2.lecteurrss;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    ArrayList<SiteRSS> mesDonnees;
    ListView listView;
    Button btnAjouter;
    EditText txtUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAjouter = findViewById(R.id.btnAddRSS);
        listView = findViewById(R.id.lstView);
        txtUrl = findViewById(R.id.txtUrl);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SiteRSS nouveauSite = new SiteRSS(txtUrl.getText().toString());

                    mesDonnees.add(nouveauSite);

                    updateAdapter();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<SiteRSS>  mondata = new ArrayList<SiteRSS>();
        mesDonnees = new ArrayList<SiteRSS>();
        try {
            FileInputStream fis = getApplicationContext().openFileInput("MesDonnees");
            ObjectInputStream is = new ObjectInputStream(fis);
            mondata = (ArrayList<SiteRSS>) is.readObject();
            for(SiteRSS site:mondata){
                mesDonnees.add(site);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        updateAdapter();
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            FileOutputStream fos = getApplicationContext().openFileOutput("MesDonnees", Context.MODE_PRIVATE);
            ObjectOutputStream od = new ObjectOutputStream(fos);
            od.writeObject(mesDonnees);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateAdapter(){
        ArrayAdapter<SiteRSS> aa = new SiteRSSAdapter(this,0,mesDonnees);

        listView.setAdapter(aa);
    }
}
