package com.example.majun.sqlitetest.room;

import java.util.List;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * Created by majun on 17/11/21.
 */

@Dao
public interface RoomTestDao {

    @Query("SELECT * FROM roomtable")
    List<RoomTestBean> queryAll();

    @Query("SELECT * FROM roomtable WHERE age> :age")
    List<RoomTestBean> queryOldPerson(int age);

    @Update
    void update(RoomTestBean... roomTestBeans);

    @Delete
    void delete(RoomTestBean... roomTestBeans);

    @Insert
    void insert(RoomTestBean... roomTestBeans);
}
