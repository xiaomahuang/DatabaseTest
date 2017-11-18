package com.example.majun.sqlitetest.realm;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.example.majun.sqlitetest.Person;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.majun.sqlitetest.MainActivity.COUNT;

/**
 * Created by majun on 17/11/17.
 */

public class RealmManager {

    private Realm mRealm;

    public RealmManager() {
        mRealm = Realm.getDefaultInstance();
    }

    public void insertRealm() {
        List<RealmTeatBean> realmTeatBeans = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            RealmTeatBean realmTeatBean = new RealmTeatBean();
            realmTeatBean.age = i;
            realmTeatBean.name = "majun";
            realmTeatBean.info = "简介：realm";
            realmTeatBeans.add(realmTeatBean);
        }
        float currentTime = System.nanoTime();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(realmTeatBeans);
        realm.commitTransaction();
        Log.d("sqlite_demo", "realmtime：" + (System.nanoTime() - currentTime) / 1000000000);
        realm.close();
    }

    public List<RealmTeatBean> getUserAll() {
        return mRealm.where(RealmTeatBean.class).findAll();
    }

    public void update(Person person) {
        float currentTime = System.nanoTime();
        mRealm.beginTransaction();
        RealmResults<RealmTeatBean> realmTeatBeans = mRealm.where(RealmTeatBean.class).greaterThanOrEqualTo("age", person.getAge()).findAll();
        Log.d("sqlite_demo", "realmSearchtime：" + (System.nanoTime() - currentTime) / 1000000000);
        for (RealmTeatBean realmTeatBean : realmTeatBeans) {
            realmTeatBean.info = person.getInfo();
        }
        mRealm.commitTransaction();
        Log.d("sqlite_demo", "realmtime：" + (System.nanoTime() - currentTime) / 1000000000);
    }

    public void deleteOldPerson(Person person) {
        float currentTime = System.nanoTime();
        final RealmResults<RealmTeatBean> results = mRealm.where(RealmTeatBean.class).greaterThanOrEqualTo("age", person.getAge()).findAll();
        Log.d("sqlite_demo", "realmSearchtime：" + (System.nanoTime() - currentTime) / 1000000000);
        mRealm.beginTransaction();
        results.deleteAllFromRealm();
        mRealm.commitTransaction();
        Log.d("sqlite_demo", "realmtime：" + (System.nanoTime() - currentTime) / 1000000000);
    }

    public void onDestory() {
        mRealm.close();
    }
}
