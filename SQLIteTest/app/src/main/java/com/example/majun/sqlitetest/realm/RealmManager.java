package com.example.majun.sqlitetest.realm;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import io.realm.Realm;

import static com.example.majun.sqlitetest.MainActivity.COUNT;

/**
 * Created by majun on 17/11/17.
 */

public class RealmManager {

    private List<RealmTeatBean> mRealmTeatBeans;

    public void insertRealm() {
        mRealmTeatBeans = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            RealmTeatBean realmTeatBean = new RealmTeatBean();
            realmTeatBean.age = i;
            realmTeatBean.name = "majun";
            realmTeatBean.info = "qwertyuiopasdfghjklzxcvbnm";
            mRealmTeatBeans.add(realmTeatBean);
        }
        float currentTime = System.nanoTime();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(mRealmTeatBeans);
        realm.commitTransaction();
        Log.d("sqlite_demo", "realmtime：" + (System.nanoTime() - currentTime) / 1000000000);
        realm.close();
    }
}
