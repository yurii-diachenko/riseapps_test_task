package com.ups.riseappstesttask;

import android.app.Application;

import com.ups.riseappstesttask.model.repositories.RealmContactRepository;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Yuriy Diachenko on 11.09.2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        RealmContactRepository.init(this);
    }
}
