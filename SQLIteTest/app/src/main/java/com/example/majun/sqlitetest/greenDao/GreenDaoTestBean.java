package com.example.majun.sqlitetest.greenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by majun on 17/11/16.
 */
@Entity
public class GreenDaoTestBean {

    //这边的id的类型必须设置成Long，如果设置自增长的话就不用设置具体的ID的值了
    @Id(autoincrement = true)
    private Long id;

    private String name;

    private long age;

    private String info;

    @Generated(hash = 77176132)
    public GreenDaoTestBean(Long id, String name, long age, String info) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.info = info;
    }

    @Generated(hash = 1284949051)
    public GreenDaoTestBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return this.age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
