package com.example.majun.sqlitetest.room;

import java.util.List;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.example.majun.sqlitetest.Person;

import static com.example.majun.sqlitetest.MainActivity.COUNT;

/**
 * Created by majun on 17/11/21.
 */

public class RoomManager {

    RoomTestDao mRoomTestDao;

    public RoomManager(Context context) {
        AppDataBase db = Room.databaseBuilder(context, AppDataBase.class, "RoomDao.db").build();
        mRoomTestDao = db.roomTestDao();
    }

    public void insert() {
        RoomTestBean[] roomTestBeans = new RoomTestBean[COUNT];
//        List<RoomTestBean> roomTestBeans = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            RoomTestBean roomTestBean = new RoomTestBean();
            roomTestBean.age = i;
            roomTestBean.info = "简介：Room";
            roomTestBean.name = "majun";
            roomTestBeans[i] = roomTestBean;
        }
        float currentTime = System.nanoTime();
        mRoomTestDao.insert(roomTestBeans);
        Log.d("sqlite_demo", "Roomtime：" + (System.nanoTime() - currentTime) / 1000000000);
    }

    public void update(Person person) {
        float currentTime = System.nanoTime();
        List<RoomTestBean> roomTestBeans = mRoomTestDao.queryOldPerson(person.getAge());
        Log.d("sqlite_demo", "RoomSearchTime：" + (System.nanoTime() - currentTime) / 1000000000);
        mRoomTestDao.update(roomTestBeans.toArray(new RoomTestBean[roomTestBeans.size()]));
        Log.d("sqlite_demo", "Roomtime：" + (System.nanoTime() - currentTime) / 1000000000);
    }

    public void delete(Person person) {
        float currentTime = System.nanoTime();
        List<RoomTestBean> roomTestBeans = mRoomTestDao.queryOldPerson(person.getAge());
        Log.d("sqlite_demo", "RoomSearchTime：" + (System.nanoTime() - currentTime) / 1000000000);
        mRoomTestDao.delete(roomTestBeans.toArray(new RoomTestBean[roomTestBeans.size()]));
        Log.d("sqlite_demo", "Roomtime：" + (System.nanoTime() - currentTime) / 1000000000);
    }

    public List<RoomTestBean> getAllUser() {
        return mRoomTestDao.queryAll();
    }
}
