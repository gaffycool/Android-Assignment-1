package com.example.tae.androidassignment1;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by TAE on 02-Feb-18.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        configRealm();
    }

    public void configRealm(){

        /**
         * realm.init
         */

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);


    }
}
