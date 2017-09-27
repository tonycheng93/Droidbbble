package com.sky.droidbbble.ui.user.usercenter;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.dribbble.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserCenterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_user_center_following_count)
    TextView mTvUserCenterFollowingCount;
    @BindView(R.id.tv_user_center_following)
    TextView mTvUserCenterFollowing;
    @BindView(R.id.iv_user_center_avatar)
    ImageView mIvUserCenterAvatar;
    @BindView(R.id.tv_user_center_follower_count)
    TextView mTvUserCenterFollowerCount;
    @BindView(R.id.tv_user_center_follower)
    TextView mTvUserCenterFollower;
    @BindView(R.id.tv_user_center_name)
    TextView mTvUserCenterName;
    @BindView(R.id.tv_user_center_desc)
    TextView mTvUserCenterDesc;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
    }
}
