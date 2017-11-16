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

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    private DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        mDataManager = new DataManager(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataManager.closeDatabase();
    }

    public void add(View view) {
        List<Person> persons = new ArrayList<>();

        Person person1 = new Person("Ella", 22, "lively girl");
        Person person2 = new Person("Jenny", 22, "beautiful girl");
        Person person3 = new Person("Jessica", 23, "sexy girl");
        Person person4 = new Person("Kelly", 23, "hot baby");
        Person person5 = new Person("Jane", 25, "a pretty woman");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);

        mDataManager.addPersons(persons);
    }

    public void update(View view) {
        Person person = new Person();
        person.name = "Jane";
        person.age = 30;
        mDataManager.updateAge(person);
    }

    public void delete(View view) {
        Person person = new Person();
        person.age = 0;
        mDataManager.deleteOldPerson(person);
    }

    public void query(View view) {
        List<Person> persons = mDataManager.getPersons();
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

    public void queryTheCursor(View view) {
        Cursor c = mDataManager.getDatabaseCursor();
        startManagingCursor(c); //托付给activity根据自己的生命周期去管理Cursor的生命周期
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

    public void insertTest(View view) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                mDataManager.insertWithPreCompiledStatement();
                mDataManager.insertGreenDao();
                mDataManager.insertRealm();
            }
        };
//        Thread thread2 = new Thread() {
//            @Override
//            public void run() {
//                mDataManager.insertGreenDao();
//            }
//        };
//        thread2.start();
        thread1.start();
    }

    public void deleteTable(View view) {
        mDataManager.deleteTable();
    }

}
