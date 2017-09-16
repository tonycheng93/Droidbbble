package com.sky.droidbbble.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tonycheng on 2017/9/8.
 */

public class Shots implements Parcelable {

    private int id;

    private String title;

    private String description;

    private int width;

    private int height;

    private Image images;

    @SerializedName("views_count")
    private int viewsCount;

    @SerializedName("likes_count")
    private int likesCount;

    @SerializedName("comments_count")
    private int commentsCount;

    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Shots{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", images=" + images +
                ", viewsCount=" + viewsCount +
                ", likesCount=" + likesCount +
                ", commentsCount=" + commentsCount +
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
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeParcelable(this.images, flags);
        dest.writeInt(this.viewsCount);
        dest.writeInt(this.likesCount);
        dest.writeInt(this.commentsCount);
        dest.writeParcelable(this.user, flags);
    }

    public Shots() {
    }

    protected Shots(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.images = in.readParcelable(Image.class.getClassLoader());
        this.viewsCount = in.readInt();
        this.likesCount = in.readInt();
        this.commentsCount = in.readInt();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Shots> CREATOR = new Creator<Shots>() {
        @Override
        public Shots createFromParcel(Parcel source) {
            return new Shots(source);
        }

        @Override
        public Shots[] newArray(int size) {
            return new Shots[size];
        }
    };
}
