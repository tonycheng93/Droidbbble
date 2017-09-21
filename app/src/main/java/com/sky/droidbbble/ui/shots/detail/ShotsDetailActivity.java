package com.sky.droidbbble.ui.shots.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.sky.dribbble.R;
import com.sky.droidbbble.data.model.Shots;
import com.sky.imageloader.ImageLoaderFactory;

import timber.log.Timber;


public class ShotsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_detail);

        final Shots shots = parseIntent();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        final String url = shots.getImages().getNormal();
        Timber.d(url);
        ImageView imageView = (ImageView) findViewById(R.id.iv_shots_detail);
        ImageLoaderFactory.getImageLoader()
                .with(this)
                .load(url)
                .setScaleType(ImageView.ScaleType.CENTER_CROP)
                .into(imageView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private Shots parseIntent() {
        final Shots shot = getIntent().getParcelableExtra("item");
        Timber.d(shot.toString());
        if (shot == null) {
            return null;
        }
        return shot;
    }
}
