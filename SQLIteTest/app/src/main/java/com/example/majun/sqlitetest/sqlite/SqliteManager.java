package com.example.majun.sqlitetest.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.majun.sqlitetest.MyApplication;
import com.example.majun.sqlitetest.Person;
import com.example.majun.sqlitetest.realm.RealmTeatBean;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.majun.sqlitetest.MainActivity.COUNT;

/**
 * Created by majun on 16/12/15.
 */
public class SqliteManager {

    private SQHelper sqHelper;

    private SQLiteDatabase sqLiteDatabase;


    public SqliteManager(Context context) {
        sqHelper = new SQHelper(context);
        sqLiteDatabase = sqHelper.getWritableDatabase();
    }

    public void addPersons() {
        insertPersonsWithPreCompiledStatement();
    }

    void insertPersonsWithNormal() {
        List<Person> persons = new ArrayList<>();
        int count = 0;
        while (count < 10000) {
            count++;
            Person person = new Person("majun", count, "qwertyuiopasdfghjklzxcvbnm");
            persons.add(person);
        }
        //开启事务
        sqLiteDatabase.beginTransaction();
        try {
            for (Person person : persons) {
                sqLiteDatabase.execSQL("INSERT INTO person VALUES(NULL,?,?,?)", new Object[]{person.getName(), person.getAge(), person.getInfo()});
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    void insertPersonsWithPreCompiledStatement() {
        String sql = "INSERT OR REPLACE INTO person (name,age,info) values (?,?,?)";
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
        int count = 0;
        float currentTime = System.nanoTime();
        sqLiteDatabase.beginTransaction();
        try {
            while (count < COUNT) {
                count++;
                sqLiteStatement.clearBindings();
                sqLiteStatement.bindString(1, "majun");
                sqLiteStatement.bindLong(2, count);
                sqLiteStatement.bindString(3, "qwertyuiopasdfghjklzxcvbnm");
                sqLiteStatement.executeInsert();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
        Log.d("sqlite_demo", "time：" + (System.nanoTime() - currentTime) / 1000000000);
    }

    public void update(Person person) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("info", person.getInfo());
        sqLiteDatabase.update("person", contentValues, "age>?", new String[]{String.valueOf(person.getAge())});
    }

    public void deleteOldPerson(Person person) {
        sqLiteDatabase.delete("person", "age>=?", new String[]{String.valueOf(person.getAge())});
    }

    public Cursor getDatabaseCursor() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM person", null);
        return cursor;
    }

    public List<Person> getPersons() {
        Cursor cursor = getDatabaseCursor();
        List<Person> persons = new ArrayList<>();
        while (cursor.moveToNext()) {
            Person person = new Person();
            person.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            person.setName(cursor.getString(cursor.getColumnIndex("name")));
            person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            person.setInfo(cursor.getString(cursor.getColumnIndex("info")));
            persons.add(person);
        }
        return persons;
    }

    public void deleteTable() {
        sqLiteDatabase.execSQL("DROP TABLE person");
        float currentTime = System.nanoTime();
        MyApplication.getDaoSession().getGreenDaoTestBeanDao().deleteAll();
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<RealmTeatBean> results = realm.where(RealmTeatBean.class)
                .findAll();
        realm.beginTransaction();
        results.createSnapshot().deleteAllFromRealm();
        realm.commitTransaction();
    }

    public void closeDatabase() {
        sqLiteDatabase.close();
    }
}
