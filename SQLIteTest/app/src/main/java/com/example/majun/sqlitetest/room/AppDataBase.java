package com.example.majun.sqlitetest.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by majun on 17/11/21.
 */

@Database(entities = {RoomTestBean.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract RoomTestDao roomTestDao();
}
