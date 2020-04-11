package com.example.blindwalls21.factories;

import com.example.blindwalls21.model.Mural;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

/***
 * This class has a method that is used for parsing the received JSON.
 * It has been done this way to prevent duplicate code for parsing the received JSON.
 */
 abstract class MuralFactory {

    private String language;

    MuralFactory(){
        setLanguage();
    }

    Mural getJsonMural(JSONObject json) {

        Mural mural = null;
        try {
            JSONArray images = json.getJSONArray("images");
            String[] imageUrl = new String[images.length()];
            for (int i = 0; i < images.length(); i++) {
                String baseImgUrl = "https://api.blindwalls.gallery/";
                imageUrl[i] = baseImgUrl + ((JSONObject) images.get(i)).getString("url");
            }

            mural = new Mural(
                    json.getInt("id"),
                    json.getJSONObject("title").getString(this.language),
                    json.getString("author"),
                    json.getJSONObject("description").getString(this.language),
                    imageUrl,
                    json.getInt("year"),
                    json.getString("photographer"),
                    this.language
            );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mural;
    }

    private void setLanguage(){
        //sets the language tho this language of the system can be expanded
        switch (Locale.getDefault().getDisplayLanguage()){

            case "Nederlands" :
                this.language = "nl";
                break;
            case "English" :
                this.language = "en";
        }
    }
}
