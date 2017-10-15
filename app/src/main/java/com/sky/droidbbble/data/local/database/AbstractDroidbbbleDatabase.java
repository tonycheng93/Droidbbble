package com.sky.droidbbble.data.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sky.droidbbble.data.local.UserDao;
import com.sky.droidbbble.data.model.User;

/**
 * Created by tonycheng on 2017/9/1.
 */

@Database(entities = {User.class}, version = 1)
public abstract class AbstractDroidbbbleDatabase extends RoomDatabase {

    abstract public UserDao userDao();
}
