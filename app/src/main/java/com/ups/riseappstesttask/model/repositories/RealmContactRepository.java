package com.ups.riseappstesttask.model.repositories;

import android.app.Application;

import com.ups.riseappstesttask.model.entities.Contact;
import com.ups.riseappstesttask.model.repositories.interfaces.IContactRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Yuriy Diachenko on 11.09.2016.
 */
public class RealmContactRepository implements IContactRepository {
    private static RealmContactRepository ourInstance;
    private Realm realm;

    public static RealmContactRepository getInstance() {
        return ourInstance;
    }

    private RealmContactRepository() {
        realm = Realm.getDefaultInstance();
    }

    public static void init(Application application) {
        ourInstance = new RealmContactRepository();
    }

    @Override
    public void addContact(Contact contact) {
        realm.beginTransaction();
        realm.insert(contact);
        realm.commitTransaction();
    }

    @Override
    public void removeContact(Contact contact) {
        contact.deleteFromRealm();
    }

    @Override
    public void updateContact(Contact contact) {
        realm.beginTransaction();
        realm.insertOrUpdate(contact);
        realm.commitTransaction();
    }

    @Override
    public List<Contact> getContacts() {
        RealmResults<Contact> results = realm.where(Contact.class)
                .findAll();
        return Arrays.asList(results.toArray(new Contact[results.size()]));
    }
}
