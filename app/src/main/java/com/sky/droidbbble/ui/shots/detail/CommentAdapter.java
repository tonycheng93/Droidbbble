package com.sky.droidbbble.ui.shots.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.dribbble.R;
import com.sky.droidbbble.data.model.Comment;
import com.sky.droidbbble.data.model.User;
import com.sky.droidbbble.utils.DateUtil;
import com.sky.imageloader.ImageLoaderFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tonycheng on 2017/9/22.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<Comment> mValues;

    private Context mContext;

    public CommentAdapter(Context context) {
        mContext = context;
        mValues = Collections.emptyList();
    }

    public void setData(List<Comment> commentList) {
        mValues = commentList;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_shots_detail_comment,
                parent, false);

        return new CommentViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        final Comment comment = mValues.get(position);
        holder.bindItem(comment);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.iv_shots_detail_comment_avatar)
        ImageView mIvAvatar;
        @BindView(R.id.tv_shots_detail_comment_author)
        TextView mTvAuthor;
        @BindView(R.id.tv_shots_detail_comment_content)
        TextView mTvContent;
        @BindView(R.id.tv_shots_detail_comment_time)
        TextView mTvTime;
        @BindView(R.id.tv_shots_detail_comment_favorite_count)
        TextView mTvFavCount;

        public Comment mComment;

        public CommentViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(Comment comment) {
            if (comment == null) {
                return;
            }
            final User user = comment.getUser();
            if (user != null) {
                final String avatarUrl = user.getAvatarUrl();
                if (!TextUtils.isEmpty(avatarUrl)) {
                    ImageLoaderFactory.getImageLoader()
                            .with(mContext)
                            .load(avatarUrl)
                            .circle(60)
                            .setScaleType(ImageView.ScaleType.CENTER_CROP)
                            .into(mIvAvatar);
                }
                final String userName = user.getName();
                if (!TextUtils.isEmpty(userName)) {
                    mTvAuthor.setText(userName);
                }
            }
            final String content = comment.getBody();
            if (!TextUtils.isEmpty(content)) {
                mTvContent.setText(Html.fromHtml(content));
            }

            final Date createdAt = comment.getCreatedAt();
            if (createdAt != null) {
                final long time = createdAt.getTime();
                mTvTime.setText(DateUtil.parseTime(time));
            }
            final int likesCount = comment.getLikesCount();
            mTvFavCount.setText(String.valueOf(likesCount));
        }
    }
}
