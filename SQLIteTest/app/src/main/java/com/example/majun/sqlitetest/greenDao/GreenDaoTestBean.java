package com.example.majun.sqlitetest.greenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by majun on 17/11/16.
 */
@Entity
public class GreenDaoTestBean {

//    @Id(autoincrement = true)
//    private long id;

    private String s1;

    private String s2;

    private String s3;

    private String s4;

    private String s5;

    private String s6;

    private String s7;

    private String s8;

    private String s9;

    @Generated(hash = 773272787)
    public GreenDaoTestBean(String s1, String s2, String s3, String s4, String s5,
            String s6, String s7, String s8, String s9) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.s9 = s9;
    }

    @Generated(hash = 1284949051)
    public GreenDaoTestBean() {
    }


    public String getS1() {
        return this.s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return this.s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return this.s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return this.s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return this.s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return this.s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public String getS7() {
        return this.s7;
    }

    public void setS7(String s7) {
        this.s7 = s7;
    }

    public String getS8() {
        return this.s8;
    }

    public void setS8(String s8) {
        this.s8 = s8;
    }

    public String getS9() {
        return this.s9;
    }

    public void setS9(String s9) {
        this.s9 = s9;
    }
}
