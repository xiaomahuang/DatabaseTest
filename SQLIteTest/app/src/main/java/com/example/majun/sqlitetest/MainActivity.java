package com.example.majun.sqlitetest;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.majun.sqlitetest.greenDao.GreenDaoManager;
import com.example.majun.sqlitetest.realm.RealmManager;
import com.example.majun.sqlitetest.sqlite.SqliteManager;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    private SqliteManager mSqliteManager;

    private GreenDaoManager mGreenDaoManager;

    private RealmManager mRealmManager;

    public static Integer COUNT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        mSqliteManager = new SqliteManager(this);
        mGreenDaoManager = new GreenDaoManager();
        mRealmManager = new RealmManager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSqliteManager.closeDatabase();
    }

    public void add(View view) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                mSqliteManager.addPersons();
                mGreenDaoManager.insertGreenDao();
                mRealmManager.insertRealm();
            }
        };
        thread1.start();
    }

    public void update(View view) {
        Person person = new Person();
        person.age = 3;
        person.info = "不错哦，6666666";
        mSqliteManager.update(person);
        mGreenDaoManager.update(person);
    }

    public void delete(View view) {
        Person person = new Person();
        person.age = 0;
        mSqliteManager.deleteOldPerson(person);
    }

    public void query(View view) {
        List<Person> persons = mSqliteManager.getPersons();
        ArrayList<Map<String, String>> list = new ArrayList<>();
        for (Person person : persons) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", person.name);
            map.put("info", person.age + " years old, " + person.info);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,
                new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
        mListView.setAdapter(adapter);
    }

    public void deleteTable(View view) {
        mSqliteManager.deleteTable();
    }

    /**
     * 托付给activity根据自己的生命周期去管理Cursor的生命周期
     */
    public void queryTheCursor() {
        Cursor c = mSqliteManager.getDatabaseCursor();
        startManagingCursor(c);
        CursorWrapper cursorWrapper = new CursorWrapper(c) {
            @Override
            public String getString(int columnIndex) {
                //将简介前加上年龄
                if (getColumnName(columnIndex).equals("info")) {
                    int age = getInt(getColumnIndex("age"));
                    return age + " years old, " + super.getString(columnIndex);
                }
                return super.getString(columnIndex);
            }
        };
        //确保查询结果中有"_id"列
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                cursorWrapper, new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

}
