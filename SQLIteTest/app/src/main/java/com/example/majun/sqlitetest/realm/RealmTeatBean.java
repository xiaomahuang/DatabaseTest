package com.example.majun.sqlitetest.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by majun on 17/11/16.
 */

public class RealmTeatBean extends RealmObject {

    //Realm使用@PrimaryKey标识主键，不支持自增主键（变相解决了多线程下主键自增可能会重复的问题），可使用唯一的uuid作为主键
//    @PrimaryKey
//    public long id;

    public String name;

    public long age;

    public String info;
}
