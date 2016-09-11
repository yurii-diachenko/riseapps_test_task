package com.ups.riseappstesttask.model.repositories.interfaces;

import com.ups.riseappstesttask.model.entities.Contact;

import java.util.List;

/**
 * Created by Yuriy Diachenko on 11.09.2016.
 */
public interface IContactRepository {

    void addContact(Contact contact);

    void removeContact(Contact contact);

    void updateContact(Contact contact);

    List<Contact> getContacts();
}
