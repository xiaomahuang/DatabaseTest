package com.example.majun.sqlitetest.greenDao;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.example.majun.sqlitetest.MyApplication;
import com.example.majun.sqlitetest.Person;
import com.example.majun.sqlitetest.greenDao.GreenDaoTestBeanDao.Properties;

import static com.example.majun.sqlitetest.MainActivity.COUNT;

/**
 * Created by majun on 17/11/17.
 */

public class GreenDaoManager {

    private GreenDaoTestBeanDao mDao;

    public GreenDaoManager() {
        mDao = MyApplication.getDaoSession().getGreenDaoTestBeanDao();
    }

    public void insertGreenDao() {
        //每次插入都要实例化新的，不然会跟原来的数据发生冲突
        List<GreenDaoTestBean> GreenDaoTestBeans = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            GreenDaoTestBean greenDaoTestBean = new GreenDaoTestBean(null, "majun", i, "简介：greenDao");
            GreenDaoTestBeans.add(greenDaoTestBean);
        }
        float currentTime = System.nanoTime();
        mDao.insertInTx(GreenDaoTestBeans);
        Log.d("sqlite_demo", "GreenDaotime：" + (System.nanoTime() - currentTime) / 1000000000);
    }

    public List<GreenDaoTestBean> getUserAll() {
        return mDao.loadAll();
    }

    public void deleteOldPerson(Person person) {
        float currentTime = System.nanoTime();
        List<GreenDaoTestBean> greenDaoTestBeans = mDao.queryBuilder().where(Properties.Age.ge(person.getAge())).list();
        Log.d("sqlite_demo", "GreenDaoSearchTime：" + (System.nanoTime() - currentTime) / 1000000000);
        mDao.deleteInTx(greenDaoTestBeans);
        Log.d("sqlite_demo", "GreenDaotime：" + (System.nanoTime() - currentTime) / 1000000000);
    }

    public void update(Person person) {
        float currentTime = System.nanoTime();
        List<GreenDaoTestBean> greenDaoTestBeans = mDao.queryBuilder().where(Properties.Age.gt(person.getAge())).list();
        Log.d("sqlite_demo", "GreenDaoSearchTime：" + (System.nanoTime() - currentTime) / 1000000000);
        for (GreenDaoTestBean greenDaoTestBean : greenDaoTestBeans) {
            greenDaoTestBean.setInfo(person.getInfo());
        }
        mDao.updateInTx(greenDaoTestBeans);
        Log.d("sqlite_demo", "GreenDaotime：" + (System.nanoTime() - currentTime) / 1000000000);
    }

}
