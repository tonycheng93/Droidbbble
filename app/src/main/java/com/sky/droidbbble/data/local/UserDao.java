//package com.sky.dribbble.data.local;
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.OnConflictStrategy;
//import android.arch.persistence.room.Query;
//
//import User;
//
//import io.reactivex.Single;
//
///**
// * Created by tonycheng on 2017/9/1.
// */
//
//@Dao
//public interface UserDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void save(User user);
//
//    @Query("SELECT * FROM user")
//    Single<User> load();
//}
