package com.example.majun.sqlitetest.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by majun on 17/11/16.
 */

@Entity(tableName = "roomtable")
public class RoomTestBean {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;

    public long age;

    public String info;
}
