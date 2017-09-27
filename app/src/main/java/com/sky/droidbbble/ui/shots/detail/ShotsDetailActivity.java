package com.sky.droidbbble.ui.shots.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.dribbble.R;
import com.sky.droidbbble.data.model.Comment;
import com.sky.droidbbble.data.model.Image;
import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.data.model.User;
import com.sky.imageloader.ImageLoaderFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShotsDetailActivity extends AppCompatActivity implements ICommentView {

    @BindView(R.id.iv_shots_detail)
    ImageView mIvImage;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.iv_shots_detail_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_shots_detail_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_shots_detail_time)
    TextView mTvTime;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private CommentAdapter mCommentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_detail);
        ButterKnife.bind(this);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCommentAdapter = new CommentAdapter(this);
        mRecyclerView.setAdapter(mCommentAdapter);

        final Shots shots = getIntent().getParcelableExtra("item");
        if (shots != null) {
            final Image images = shots.getImages();
            if (images != null) {
                final String url = images.getNormal();
                if (!TextUtils.isEmpty(url)) {
                    ImageLoaderFactory.getImageLoader()
                            .with(this)
                            .load(url)
                            .into(mIvImage);
                }
            }
            final User user = shots.getUser();
            if (user != null) {
                final String avatarUrl = user.getAvatarUrl();
                if (!TextUtils.isEmpty(avatarUrl)) {
                    ImageLoaderFactory.getImageLoader()
                            .with(this)
                            .load(avatarUrl)
                            .setScaleType(ImageView.ScaleType.CENTER_CROP)
                            .circle(60)
                            .into(mIvAvatar);
                }
                final String name = user.getName();
                if (!TextUtils.isEmpty(name)) {
                    mTvAuthor.setText(name);
                }
                mTvTime.setText("2017-09-22");
            }
            final int shotsId = shots.getId();
            CommentPresenter commentPresenter = new CommentPresenter();
            commentPresenter.attachView(this);
            commentPresenter.getComments(shotsId);
        }

    }

    @Override
    public void showComments(List<Comment> commentList) {
        mCommentAdapter.setData(commentList);
        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
