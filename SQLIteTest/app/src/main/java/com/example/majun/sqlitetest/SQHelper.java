package com.example.majun.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by majun on 16/12/15.
 */
public class SQHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "testbase";

    private static final int VERSION = 1;

    /**
     * 这边并没有创建数据库，只是将数据库相关的信息传递了进去。
     */
    public SQHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * 创建表
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS person(_id INTEGER NOT NULL PRIMARY KEY,name VARCHAR,age INTEGER,info TEXT,name2 VARCHAR,"
//                + "age2 INTEGER,info2 TEXT,name3 VARCHAR,age3 INTEGER,info3 TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS person(_id INTEGER NOT NULL PRIMARY KEY,s1 TEXT,s2 TEXT,s3 TEXT,s4 TEXT,"
                + "s5 TEXT,s6 TEXT,s7 TEXT,s8 TEXT,s9 TEXT)");
    }

    /**
     * 当数据库版本发生变化时，调用onUpdate。
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("ALTER TABLE person ADD COLUMN birthday DATE");
    }

    /**
     * 下面两个方法主要用来创建或打开数据库，和oncreate方法的调用（也就是表的创建）
     */
    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }



}
