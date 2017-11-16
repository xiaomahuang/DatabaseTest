package com.example.majun.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import com.example.majun.sqlitetest.greenDao.GreenDaoTestBean;
import com.example.majun.sqlitetest.realm.RealmTeatBean;

import io.realm.Realm;

/**
 * Created by majun on 16/12/15.
 */
public class DataManager {

    private SQHelper sqHelper;

    private SQLiteDatabase sqLiteDatabase;

    private List<GreenDaoTestBean> mGreenDaoTestBeans = new ArrayList<>();

    private List<RealmTeatBean> mRealmTeatBeans = new ArrayList<>();

    public DataManager(Context context) {
        sqHelper = new SQHelper(context);
        sqLiteDatabase = sqHelper.getWritableDatabase();


    }

    public void addPersons(List<Person> persons) {
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

    public void updateAge(Person person) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("age", person.getAge());
        sqLiteDatabase.update("person", contentValues, "name=?", new String[]{person.getName()});
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

    void insertWithPreCompiledStatement() {
//        String sql = "INSERT OR REPLACE INTO person (s1,s2,s3) values (?,?,?,?,?,?,?,?,?)";
        String sql = "INSERT OR REPLACE INTO person (s1,s2,s3,s4,s5,s6,s7,s8,s9) values (?,?,?,?,?,?,?,?,?)";

        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
        int count = 0;
        float currentTime = System.nanoTime();
//        ContentValues values = new ContentValues();
//        values.put("name", "majun");
//        values.put("age", "11");
//        values.put("info", "wulalalalal");
        String test = makeLargeJson();
        sqLiteDatabase.beginTransaction();
        try {
            while (count < 10000) {
                count++;
                sqLiteStatement.clearBindings();
                sqLiteStatement.bindString(1, "qwertyuiopasdfghjklzxcvbnm");
                sqLiteStatement.bindString(2, "qwertyuiopasdfghjklzxcvbnm");
                sqLiteStatement.bindString(3, "qwertyuiopasdfghjklzxcvbnm");
                sqLiteStatement.bindString(4, "qwertyuiopasdfghjklzxcvbnm");
                sqLiteStatement.bindString(5, "qwertyuiopasdfghjklzxcvbnm");
                sqLiteStatement.bindString(6, "qwertyuiopasdfghjklzxcvbnm");
                sqLiteStatement.bindString(7, "qwertyuiopasdfghjklzxcvbnm");
                sqLiteStatement.bindString(8, "qwertyuiopasdfghjklzxcvbnm");
                sqLiteStatement.bindString(9, "qwertyuiopasdfghjklzxcvbnm");
                sqLiteStatement.executeInsert();
//            sqLiteDatabase.insert("person", null, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
        Log.d("sqlite_demo", "time：" + (System.nanoTime() - currentTime) / 1000000000);
    }

    void insertGreenDao() {
        for (int i = 0; i < 10000; i++) {
            GreenDaoTestBean greenDaoTestBean = new GreenDaoTestBean();
            greenDaoTestBean.setS1("qwertyuiopasdfghjklzxcvbnm");
            greenDaoTestBean.setS2("qwertyuiopasdfghjklzxcvbnm");
            greenDaoTestBean.setS3("qwertyuiopasdfghjklzxcvbnm");
            greenDaoTestBean.setS4("qwertyuiopasdfghjklzxcvbnm");
            greenDaoTestBean.setS5("qwertyuiopasdfghjklzxcvbnm");
            greenDaoTestBean.setS6("qwertyuiopasdfghjklzxcvbnm");
            greenDaoTestBean.setS7("qwertyuiopasdfghjklzxcvbnm");
            greenDaoTestBean.setS8("qwertyuiopasdfghjklzxcvbnm");
            greenDaoTestBean.setS9("qwertyuiopasdfghjklzxcvbnm");
            mGreenDaoTestBeans.add(greenDaoTestBean);
        }
        float currentTime = System.nanoTime();
        MyApplication.getDaoSession().getGreenDaoTestBeanDao().insertInTx(mGreenDaoTestBeans);
        Log.d("sqlite_demo", "GreenDaotime：" + (System.nanoTime() - currentTime) / 1000000000);
    }

    void insertRealm() {
        for (int i = 0; i < 10000; i++) {
            RealmTeatBean realmTeatBean = new RealmTeatBean();
            realmTeatBean.setS1("qwertyuiopasdfghjklzxcvbnm");
            realmTeatBean.setS2("qwertyuiopasdfghjklzxcvbnm");
            realmTeatBean.setS3("qwertyuiopasdfghjklzxcvbnm");
            realmTeatBean.setS4("qwertyuiopasdfghjklzxcvbnm");
            realmTeatBean.setS5("qwertyuiopasdfghjklzxcvbnm");
            realmTeatBean.setS6("qwertyuiopasdfghjklzxcvbnm");
            realmTeatBean.setS7("qwertyuiopasdfghjklzxcvbnm");
            realmTeatBean.setS8("qwertyuiopasdfghjklzxcvbnm");
            realmTeatBean.setS9("qwertyuiopasdfghjklzxcvbnm");
            mRealmTeatBeans.add(realmTeatBean);
        }
        float currentTime = System.nanoTime();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(mRealmTeatBeans);
        realm.commitTransaction();
        Log.d("sqlite_demo", "realmtime：" + (System.nanoTime() - currentTime) / 1000000000);
    }

    void deleteTable() {
        sqLiteDatabase.execSQL("DROP TABLE person");
        MyApplication.getDaoSession().getGreenDaoTestBeanDao().deleteAll();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(RealmTeatBean.class);
        realm.commitTransaction();
    }

    public void closeDatabase() {
        sqLiteDatabase.close();
    }


    private String makeLargeJson() {
        Test test = new Test();
        for (int i = 0; i < 40; i++) {
            test.list.add("https://stackoverflow.com/questions/9755803/whats-the-best-sqlite-data-type-for-a-long-string");
        }
        return new Gson().toJson(test);
    }

    public class Test {

        public List<String> list = new ArrayList<>();
    }
}
