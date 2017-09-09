package com.sky.dribbble;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.sky.dribbble.data.model.Shots;
import com.sky.dribbble.data.model.User;
import com.sky.dribbble.ui.main.shots.IShotsView;
import com.sky.dribbble.ui.main.shots.ShotsPresenter;
import com.sky.dribbble.ui.user.IUserView;
import com.sky.dribbble.ui.user.UserPresenter;
import com.sky.imageloader.ImageLoaderFactory;

import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IUserView,
        IShotsView {

    private static final String TAG = "MainActivity";

    private ImageView mAvatarImageView;
    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        mAvatarImageView = (ImageView) findViewById(R.id.test_image);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mUserPresenter = new UserPresenter();
        mUserPresenter.attachView(MainActivity.this);
        mUserPresenter.getUser();

        ShotsPresenter shotsPresenter = new ShotsPresenter();
        shotsPresenter.attachView(this);
        shotsPresenter.getShots(10, 1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showUser(User user) {
        if (user == null) {
            return;
        }
        final String avatarUrl = user.getAvatarUrl();
        if (!TextUtils.isEmpty(avatarUrl)) {
            final String url = "https://cdn.dribbble" +
                    ".com/users/63407/screenshots/3780917/dribbble_aloe_vera_bloom.png";
//            ImageLoaderFactory.getImageLoader()
//                    .with(this)
//                    .asBitmap()
//                    .load(url)
//                    .setScaleType(ImageView.ScaleType.FIT_CENTER)
//                    .override(100,100)
//                    .setPlaceholder(R.mipmap.ic_launcher)
//                    .blur(10)
//                    .gray(true)
//                    .circle(10)
//                    .roundCorner(8)
//                    .colorFilter(R.color.colorPrimaryDark)
//                    .into(new FinalCallback() {
//                        @Override
//                        public void onSuccess(Object bitmap) {
//                            Log.d(TAG, "onSuccess: bitmap = " + bitmap);
//                            mAvatarImageView.setImageBitmap((Bitmap) bitmap);
//                        }
//
//                        @Override
//                        public void onFailed(Throwable throwable) {
//                            Log.d(TAG, "onFailed: " + throwable.getMessage());
//                        }
//                    });
//                    .into(mAvatarImageView);
//            GlideApp.with(this)
//                    .asFile()
//                    .load(url)
//                    .into(new SimpleTarget<File>() {
//                        @Override
//                        public void onResourceReady(File resource, Transition<? super File> transition) {
//                            Log.d(TAG, "onResourceReady: resource = " + resource.getAbsolutePath());
//                        }
//                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageLoaderFactory.destroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showShots(List<Shots> shotsList) {
        Timber.d("shotsList = " + shotsList);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }
}
