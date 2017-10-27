package com.sky.droidbbble.data.local;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.sky.droidbbble.data.local.database.BaseDroidbbbleDatabase;
import com.sky.droidbbble.data.model.User;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by tonycheng on 2017/9/29.
 */

public class DatabaseHelper {

    private BaseDroidbbbleDatabase mDatabase = null;
    private static final String DATABASE_NAME = "droidbbble.db";

    public DatabaseHelper(Context context) {
        mDatabase = Room.databaseBuilder(context.getApplicationContext(), BaseDroidbbbleDatabase.class,
                DATABASE_NAME).build();
    }

    //save user
    public Single<User> saveUser(final User user) {
        return Single.create(new SingleOnSubscribe<User>() {
            @Override
            public void subscribe(SingleEmitter<User> e) throws Exception {
                if (e.isDisposed()) {
                    return;
                }
                mDatabase.beginTransaction();
                try {
                    mDatabase.userDao().save(user);
                    mDatabase.setTransactionSuccessful();
                    e.onSuccess(user);
                } finally {
                    mDatabase.endTransaction();
                }
            }
        });
    }

    //load user
    public Single<User> getUser() {
        return mDatabase.userDao().load();
    }
}
