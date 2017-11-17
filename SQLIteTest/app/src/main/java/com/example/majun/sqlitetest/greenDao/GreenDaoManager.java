package com.example.majun.sqlitetest.greenDao;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.example.majun.sqlitetest.MyApplication;
import com.example.majun.sqlitetest.Person;

import static com.example.majun.sqlitetest.MainActivity.COUNT;

/**
 * Created by majun on 17/11/17.
 */

public class GreenDaoManager {

    private GreenDaoTestBeanDao mDao;

    private List<GreenDaoTestBean> mGreenDaoTestBeans;


    public GreenDaoManager() {
        mDao = MyApplication.getDaoSession().getGreenDaoTestBeanDao();
    }

    public void insertGreenDao() {
        //每次插入都要实例化新的，不然会跟原来的数据发生冲突
        mGreenDaoTestBeans = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            GreenDaoTestBean greenDaoTestBean = new GreenDaoTestBean(null, "majun", i, "qwertyuiopasdfghjklzxcvbnm");
            mGreenDaoTestBeans.add(greenDaoTestBean);
        }
        float currentTime = System.nanoTime();
        mDao.insertInTx(mGreenDaoTestBeans);
        Log.d("sqlite_demo", "GreenDaotime：" + (System.nanoTime() - currentTime) / 1000000000);
    }


    public List<GreenDaoTestBean> getUserAll() {
        return mDao.loadAll();
    }

    public void insert(GreenDaoTestBean user) {
        mDao.insert(user);
    }

    public void delete(GreenDaoTestBean user) {
        mDao.delete(user);
    }

    public void insertAll(List<GreenDaoTestBean> users) {
        mDao.insertInTx(users);
    }

    public void deleteAll() {
        mDao.deleteAll();
    }

    public void update(Person person) {
//        GreenDaoTestBean greenDaoTestBean = new GreenDaoTestBean();
//        mDao.update(user);
    }

}
