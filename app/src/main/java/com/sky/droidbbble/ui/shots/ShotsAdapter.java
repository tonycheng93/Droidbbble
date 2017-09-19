package com.sky.droidbbble.ui.shots;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.dribbble.R;
import com.sky.droidbbble.data.model.Image;
import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.data.model.User;
import com.sky.droidbbble.ui.shots.ShotsFragment.OnListFragmentInteractionListener;
import com.sky.droidbbble.ui.shots.detail.ShotsDetailActivity;
import com.sky.droidbbble.utils.FontsManager;
import com.sky.imageloader.ImageLoaderFactory;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Shots} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class ShotsAdapter extends RecyclerView.Adapter<ShotsAdapter.ViewHolder> {

    private static final String TAG = "ShotsAdapter";

    private Context mContext;
    private List<Shots> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ShotsAdapter(Context context, OnListFragmentInteractionListener listener) {
        mContext = context;
        mValues = Collections.emptyList();
        mListener = listener;
    }

    public void setData(List<Shots> shotsList) {
        mValues = shotsList;
    }

    public List<Shots> getData() {
        return mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_shots, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        FontsManager.changeFonts(holder.mView);
        holder.mItem = mValues.get(position);
        final Shots shots = mValues.get(position);
        if (shots == null) {
            return;
        }
        final Image images = shots.getImages();
        if (images != null) {
            final String normal = images.getNormal();
            if (!TextUtils.isEmpty(normal)) {
                ImageLoaderFactory.getImageLoader()
                        .with(mContext)
                        .load(normal)
                        .into(holder.mPhotoView);
            }
        }
        final User user = shots.getUser();
        if (user != null) {
            final String avatarUrl = user.getAvatarUrl();
            if (!TextUtils.isEmpty(avatarUrl)) {
                ImageLoaderFactory.getImageLoader()
                        .with(mContext)
                        .load(avatarUrl)
                        .circle(80)
                        .into(holder.mAvatarView);
            }
            final String name = user.getName();
            if (!TextUtils.isEmpty(name)) {
                holder.mAuthorView.setText(String.format(mContext.getResources().getString(R
                        .string.shots_author), name));
            }
        }
        final String title = shots.getTitle();
        if (!TextUtils.isEmpty(title)) {
            holder.mTitleView.setSelected(true);
            holder.mTitleView.setText(title);
        }
        holder.mWatchCountView.setText(String.valueOf(shots.getViewsCount()));
        holder.mCommentCountView.setText(String.valueOf(shots.getCommentsCount()));
        holder.mFavoriteCountView.setText(String.valueOf(shots.getLikesCount()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    mContext.startActivity(new Intent(mContext, ShotsDetailActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.iv_item_shots)
        ImageView mPhotoView;
        @BindView(R.id.iv_item_avatar)
        ImageView mAvatarView;
        @BindView(R.id.tv_item_title)
        TextView mTitleView;
        @BindView(R.id.tv_item_author)
        TextView mAuthorView;
        @BindView(R.id.tv_item_watch)
        TextView mWatchCountView;
        @BindView(R.id.tv_item_comment)
        TextView mCommentCountView;
        @BindView(R.id.tv_item_favorite)
        TextView mFavoriteCountView;

        public Shots mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, mView);
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "mView=" + mView +
                    ", mPhotoView=" + mPhotoView +
                    ", mAvatarView=" + mAvatarView +
                    ", mTitleView=" + mTitleView +
                    ", mAuthorView=" + mAuthorView +
                    ", mWatchCountView=" + mWatchCountView +
                    ", mCommentCountView=" + mCommentCountView +
                    ", mFavoriteCountView=" + mFavoriteCountView +
                    ", mItem=" + mItem +
                    '}';
        }
    }
}
