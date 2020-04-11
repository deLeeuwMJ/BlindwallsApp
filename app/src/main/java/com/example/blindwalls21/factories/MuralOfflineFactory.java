package com.example.blindwalls21.factories;

import android.content.Context;
import android.util.Log;

import com.example.blindwalls21.model.Mural;
import com.example.blindwalls21.R;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/***
 * This class gets the information needed from a (offline) JSON DUMP file.
 */

public class MuralOfflineFactory extends MuralFactory{

    private static final String TAG = MuralOfflineFactory.class.getSimpleName();

    private String readStream(InputStream is) {
        StringBuilder sb = new StringBuilder(512);
        try {
            Reader r = new InputStreamReader(is, "UTF-8");
            int c = 0;
            while ((c = r.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        Log.d("JSONString", sb.toString());
        return sb.toString();
    }

    public ArrayList<Mural> fillDataSet(Context context) {

        ArrayList<Mural> murals = new ArrayList<>();

        try {
            InputStream is = context.getResources().openRawResource(R.raw.bwraw);
            JSONArray jArr = new JSONArray(readStream(is));
            for (int i = 0; i < jArr.length(); i++) {
                murals.add(super.getJsonMural(jArr.getJSONObject(i)));
                Log.d("MuralImg", murals.get(i).getImageURL()[0]);
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return murals;
    }


}
