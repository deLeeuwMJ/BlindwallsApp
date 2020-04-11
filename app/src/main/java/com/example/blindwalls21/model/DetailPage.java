package com.example.blindwalls21.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.blindwalls21.R;
import com.example.blindwalls21.factories.MuralOfflineFactory;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DetailPage extends AppCompatActivity {

    private TextView mInfo, mDetailed, mYear, mPhotographer;
    private List<SlideModel> images;
    private Mural muralObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        mInfo = findViewById(R.id.authorBw);
        mDetailed = findViewById(R.id.descBw);
        mYear = findViewById(R.id.yearBw);
        mPhotographer = findViewById(R.id.photographerBw);
        muralObj = getIntent().getParcelableExtra("selectedMural");
        images = new ArrayList<>();

        initData();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {

        mInfo.setText(muralObj.getArtist());
        mDetailed.setText(muralObj.getDescription());

        String txtPhotographer = "";
        String txtYear = "";

        switch (muralObj.getLanguageCode()){
            case "nl":
                txtPhotographer = "Fotograaf: ";
                txtYear = "Jaar: ";
                break;
            case "en":
                txtPhotographer = "Photographer: ";
                txtYear = "Year: ";
                break;
        }

        mPhotographer.setText(txtPhotographer + muralObj.getPhotographer());
        mYear.setText(txtYear + muralObj.getYear());

        mDetailed.setMovementMethod(new ScrollingMovementMethod());

        for(String imageURL : muralObj.getImageURL()){
            images.add(new SlideModel(imageURL));
        }

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        imageSlider.setImageList(images, true);
    }
}
