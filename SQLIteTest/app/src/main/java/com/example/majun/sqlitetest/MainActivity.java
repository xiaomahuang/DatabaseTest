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
import com.example.majun.sqlitetest.greenDao.GreenDaoTestBean;
import com.example.majun.sqlitetest.realm.RealmManager;
import com.example.majun.sqlitetest.realm.RealmTeatBean;
import com.example.majun.sqlitetest.room.RoomManager;
import com.example.majun.sqlitetest.room.RoomTestBean;
import com.example.majun.sqlitetest.sqlite.SqliteManager;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    private SqliteManager mSqliteManager;

    private GreenDaoManager mGreenDaoManager;

    private RealmManager mRealmManager;

    private RoomManager mRoomManager;

    public static Integer COUNT = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        mSqliteManager = new SqliteManager(this);
        mGreenDaoManager = new GreenDaoManager();
        mRealmManager = new RealmManager();
        mRoomManager = new RoomManager(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSqliteManager.closeDatabase();
        mRealmManager.onDestory();
    }

    public void add(View view) {
        new Thread() {
            @Override
            public void run() {
                mSqliteManager.addPersons();
                mGreenDaoManager.insertGreenDao();
                mRealmManager.insertRealm();
                mRoomManager.insert();
            }
        }.start();
    }

    public void update(View view) {
        final Person person = new Person();
        person.setAge(3);
        person.setInfo("不错哦，6666666");
        new Thread() {
            @Override
            public void run() {
                mSqliteManager.update(person);
                mGreenDaoManager.update(person);
                mRealmManager.update(person);
                mRoomManager.update(person);
            }
        }.start();

    }

    public void delete(View view) {
        final Person person = new Person();
        person.setAge(0);
        new Thread() {
            @Override
            public void run() {
                mSqliteManager.deleteOldPerson(person);
                mGreenDaoManager.deleteOldPerson(person);
                mRealmManager.deleteOldPerson(person);
                mRoomManager.delete(person);
            }
        }.start();
    }

    public void query(View view) {
        List<Person> persons = mSqliteManager.getPersons();
        List<GreenDaoTestBean> greenDaoTestBeans = mGreenDaoManager.getUserAll();
        List<RealmTeatBean> realmTeatBeans = mRealmManager.getUserAll();
//        List<RoomTestBean> roomTestBeans = mRoomManager.getAllUser();
        ArrayList<Map<String, String>> list = new ArrayList<>();
        for (Person person : persons) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", person.getName());
            map.put("info", person.getAge() + " years old, " + person.getInfo());
            list.add(map);
        }
        for (GreenDaoTestBean greenDaoTestBean : greenDaoTestBeans) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", greenDaoTestBean.getName());
            map.put("info", greenDaoTestBean.getAge() + " years old, " + greenDaoTestBean.getInfo());
            list.add(map);
        }
        for (RealmTeatBean realmTeatBean : realmTeatBeans) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", realmTeatBean.name);
            map.put("info", realmTeatBean.age + " years old, " + realmTeatBean.info);
            list.add(map);
        }
//        for (RoomTestBean roomTestBean : roomTestBeans) {
//            HashMap<String, String> map = new HashMap<>();
//            map.put("name", roomTestBean.name);
//            map.put("info", roomTestBean.age + " years old, " + roomTestBean.info);
//            list.add(map);
//        }
        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,
                new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
        mListView.setAdapter(adapter);
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
