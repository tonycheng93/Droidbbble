package com.sky.dribbble.data.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sky.dribbble.data.local.UserDao;
import com.sky.dribbble.data.model.User;

/**
 * Created by tonycheng on 2017/9/1.
 */

@Database(entities = {User.class}, version = 1)
public abstract class DribbbleDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
