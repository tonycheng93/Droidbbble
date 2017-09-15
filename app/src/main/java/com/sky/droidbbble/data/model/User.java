package com.sky.droidbbble.data.model;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return
                "User{" +
                        "avatar_url = '" + avatarUrl + '\'' +
                        ",html_url = '" + htmlUrl + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",username = '" + username + '\'' +
                        "}";
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
    }

    public User() {
    }

    protected User(Parcel in) {
        this.avatarUrl = in.readString();
        this.htmlUrl = in.readString();
        this.name = in.readString();
        this.id = in.readInt();
        this.username = in.readString();
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