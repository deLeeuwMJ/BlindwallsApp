package com.example.blindwalls21.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.blindwalls21.controller.MuralAdapter;
import com.example.blindwalls21.controller.MuralListener;
import com.example.blindwalls21.R;
import com.example.blindwalls21.factories.MuralOfflineFactory;
import com.example.blindwalls21.factories.MuralOnlineFactory;
import com.google.android.material.snackbar.Snackbar;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MuralListener {

    private final String TAG = getClass().getSimpleName();

    private MuralAdapter muralAdapter;
    private ArrayList<Mural> muralArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView muralRv = findViewById(R.id.recyclerViewMural);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            muralRv.setLayoutManager(new GridLayoutManager(this,3));
        }else{
            muralRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }

        muralRv.setHasFixedSize(true);

        this.muralArrayList = new ArrayList<>();

        if (isNetworkConnected()){
            //Mural using online api
            new MuralOnlineFactory(this, this).getMurals();
        } else {
            //Mural using offline json file
            muralArrayList = new MuralOfflineFactory().fillDataSet(this);
        }

        this.muralAdapter = new MuralAdapter(this.muralArrayList);
        muralRv.setAdapter(this.muralAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!isNetworkConnected()){
            showSnackBar(this, "Using offline data, because you are not connected to the internet!");
        }
    }

    private void showSnackBar(Activity activity, String message){
        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, message, 3000).show();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onMuralAvailable(Mural mural) {
        this.muralArrayList.add(mural);
        this.muralAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMuralError(Error error) {
        Log.e("Mural error in " + TAG, error.getMessage());
    }
}
