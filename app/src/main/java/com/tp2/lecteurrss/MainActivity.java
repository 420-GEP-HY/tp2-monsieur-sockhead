package com.tp2.lecteurrss;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import org.xml.sax.SAXException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
        mesDonnees = new ArrayList<SiteRSS>();
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
<<<<<<< HEAD
                            SiteRSS nouveauSite = new SiteRSS( (String)"https://ici.radio-canada.ca/rss/4159");
                            mesDonnees.add(nouveauSite);

=======
                            SiteRSS nouveauSite = new SiteRSS("https://ici.radio-canada.ca/rss/4159");
                            mesDonnees.add(nouveauSite);
                            updateAdapter();
>>>>>>> 4c27e935429246b17602ee0fa1e9bb701e4dada5
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        }
<<<<<<< HEAD
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateAdapter();

                            }
                        });
                    }
                }).start();
                //SiteRSS nouveauSite = new SiteRSS(txtUrl.getText().toString());

=======
                    }
                }).start();
>>>>>>> 4c27e935429246b17602ee0fa1e9bb701e4dada5
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
            od.close();
            fos.close();
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
