package com.example.blindwalls21.factories;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.blindwalls21.controller.MuralListener;

import org.json.JSONArray;
import org.json.JSONException;

/***
 * This class gets the information needed from the (online) Blindwalls JSON API.
 */

public class MuralOnlineFactory extends MuralFactory{
    private RequestQueue mQueue;
    private MuralListener muralListener;

    private final String TAG = getClass().getSimpleName();


    public MuralOnlineFactory(Context mContext, MuralListener listener) {
        this.mQueue = Volley.newRequestQueue(mContext);
        this.muralListener = listener;
    }

    public void getMurals() {

        String url = "https://api.blindwalls.gallery/apiv2/murals";
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        muralListener.onMuralAvailable(MuralOnlineFactory.super.getJsonMural(response.getJSONObject(i)));
                    }
                } catch (JSONException e) {
                    Log.e("JSON error in " + TAG, e.getMessage());
                    muralListener.onMuralError(new Error(e.getMessage(),e.getCause()));
                }
            }
        }, error -> Log.e("Response error in " + TAG, error.toString()));

        this.mQueue.add(request);
    }
}
