package com.tp2.lecteurrss;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

public class NouvelleRSSAdapter extends ArrayAdapter<NouvellesRSS> {
    public NouvelleRSSAdapter(@NonNull Context context, int resource, @NonNull List<NouvellesRSS> objects) {
        super(context, resource, objects);
    }
}
