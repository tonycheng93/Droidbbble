package com.sky.droidbbble.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.sky.dribbble.R;
import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.data.model.User;
import com.sky.droidbbble.ui.shots.ShotsFragment;
import com.sky.droidbbble.ui.user.IUserView;
import com.sky.droidbbble.ui.user.UserPresenter;
import com.sky.droidbbble.utils.FontsManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IUserView, ShotsFragment
        .OnListFragmentInteractionListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private UserPresenter mUserPresenter;
    private List<Fragment> mFragments;
    private List<String> mTabTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        FontsManager.changeFonts(mToolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        if (mFragments == null) {
            mFragments = new ArrayList<>();
        }
        mFragments.add(ShotsFragment.newInstance());
//        mFragments.add(ShotsFragment.newInstance());

        if (mTabTitles == null) {
            mTabTitles = new ArrayList<>();
        }
        mTabTitles.add("Shots");
        mTabTitles.add("Following");

//        mTabLayout.addTab(mTabLayout.newTab().setText("Shots"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("Following"));

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), mFragments,
                mTabTitles);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        FontsManager.changeFonts(mTabLayout);

        if (mUserPresenter == null) {
            mUserPresenter = new UserPresenter();
        }
        mUserPresenter.attachView(this);
        mUserPresenter.getUser();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
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

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showUser(User user) {
        if (user == null) {
            return;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ImageView avatarView = (ImageView) drawer.findViewById(R.id.iv_nav_avatar);
        final String avatarUrl = user.getAvatarUrl();
        if (!TextUtils.isEmpty(avatarUrl)) {
            Timber.d("avatarView = " + avatarView);
//            ImageLoaderFactory.getImageLoader()
//                    .with(this)
//                    .load(avatarUrl)
//                    .circle(100)
//                    .into(avatarView);
        }
    }

    @Override
    public void onListFragmentInteraction(Shots item) {
        if (item != null) {
            Timber.d(item.toString());
        }
    }
}
