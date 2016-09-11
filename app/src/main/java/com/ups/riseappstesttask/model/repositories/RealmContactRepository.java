package com.ups.riseappstesttask.model.repositories;

import android.app.Application;

import com.ups.riseappstesttask.model.entities.Contact;
import com.ups.riseappstesttask.model.repositories.interfaces.IContactRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

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
        long primaryKeyValue = realm.where(Contact.class).max("id").longValue();
        contact.setId(++primaryKeyValue);
        realm.beginTransaction();
        realm.insert(contact);
        realm.commitTransaction();
    }

    @Override
    public void removeContact(Contact contact) {
        realm.beginTransaction();
        contact.deleteFromRealm();
        realm.commitTransaction();
    }

    @Override
    public void updateContact(Contact contact) {
        realm.beginTransaction();
        realm.insertOrUpdate(contact);
        realm.commitTransaction();
    }

    @Override
    public List<Contact> getContacts() {
        RealmResults<Contact> results = realm.where(Contact.class).findAllSorted(new String[]{"firstName", "secondName", "sourname"}, new Sort[]{Sort.ASCENDING, Sort.ASCENDING, Sort.ASCENDING});
        return new ArrayList<>(Arrays.asList(results.toArray(new Contact[results.size()])));
    }
}
