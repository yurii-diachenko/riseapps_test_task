package com.ups.riseappstesttask.views.interfaces;

import com.ups.riseappstesttask.model.entities.Contact;

import java.util.List;

/**
 * Created by Yuriy Diachenko on 11.09.2016.
 */
public interface IContactListView extends IView{
    void setContacts(List<Contact> contacts);
}
