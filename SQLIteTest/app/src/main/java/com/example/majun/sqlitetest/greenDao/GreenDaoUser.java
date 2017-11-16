package com.example.majun.sqlitetest.greenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by majun on 17/11/16.
 */

@Entity
public class GreenDaoUser {

    @Id(autoincrement = true)
    private Long id;

    private String name;

    private int age;

    private String occupation;

    private String gender;

    private String description;

    @Generated(hash = 1568467096)
    public GreenDaoUser(Long id, String name, int age, String occupation,
            String gender, String description) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.gender = gender;
        this.description = description;
    }

    @Generated(hash = 83249558)
    public GreenDaoUser() {
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
