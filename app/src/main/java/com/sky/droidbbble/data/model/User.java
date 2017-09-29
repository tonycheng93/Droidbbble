package com.sky.droidbbble.data.model;

import com.google.gson.annotations.SerializedName;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "user")
public class User implements Parcelable {

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("name")
    private String name;

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("bio")
    private String bio;

    @SerializedName("followings_count")
    private int followingsCount;

    @SerializedName("followers_count")
    private int followersCount;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getFollowingsCount() {
        return followingsCount;
    }

    public void setFollowingsCount(int followingsCount) {
        this.followingsCount = followingsCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "avatarUrl='" + avatarUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", followingsCount=" + followingsCount +
                ", followersCount=" + followersCount +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.avatarUrl);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeString(this.username);
        dest.writeString(this.bio);
        dest.writeInt(this.followingsCount);
        dest.writeInt(this.followersCount);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.avatarUrl = in.readString();
        this.htmlUrl = in.readString();
        this.name = in.readString();
        this.id = in.readInt();
        this.username = in.readString();
        this.bio = in.readString();
        this.followingsCount = in.readInt();
        this.followersCount = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}