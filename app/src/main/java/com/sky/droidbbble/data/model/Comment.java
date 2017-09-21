package com.sky.droidbbble.data.model;

/**
 * Created by tonycheng on 2017/9/21.
 */

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * user comment
 */
public class Comment implements Parcelable {

    private int id;
    private String body;
    @SerializedName("likes_count")
    private int likesCount;
    @SerializedName("likes_url")
    private String likesUrl;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getLikesUrl() {
        return likesUrl;
    }

    public void setLikesUrl(String likesUrl) {
        this.likesUrl = likesUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", likesCount=" + likesCount +
                ", likesUrl='" + likesUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.body);
        dest.writeInt(this.likesCount);
        dest.writeString(this.likesUrl);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeParcelable(this.user, flags);
    }

    public Comment() {
    }

    protected Comment(Parcel in) {
        this.id = in.readInt();
        this.body = in.readString();
        this.likesCount = in.readInt();
        this.likesUrl = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
