package com.example.majun.sqlitetest;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.majun.sqlitetest.greenDao.DaoMaster;
import com.example.majun.sqlitetest.greenDao.DaoMaster.DevOpenHelper;
import com.example.majun.sqlitetest.greenDao.DaoSession;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by majun on 17/11/16.
 */

public class MyApplication extends Application {

    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }

    private void initGreenDao() {
        //创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DevOpenHelper(this, "dao.db", null);
        //获取可读写数据库
        SQLiteDatabase sqLiteDatabase = devOpenHelper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        //获取dao对象管理器
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
