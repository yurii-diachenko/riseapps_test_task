package com.ups.riseappstesttask.presenters;

import com.ups.riseappstesttask.model.entities.Contact;
import com.ups.riseappstesttask.model.repositories.interfaces.IContactRepository;
import com.ups.riseappstesttask.presenters.interfaces.IPresenter;
import com.ups.riseappstesttask.views.interfaces.IContactListView;

import java.util.List;

/**
 * Created by Yuriy Diachenko on 11.09.2016.
 */
public class ContactListPresenter extends IPresenter<IContactListView> {
    private IContactRepository contactRepository;
    private List<Contact> contacts;

    public ContactListPresenter(IContactListView view, IContactRepository contactRepository) {
        super(view);
        this.contactRepository = contactRepository;
    }

    @Override
    public void init() {
        contacts = contactRepository.getContacts();
        view.setContacts(contacts);
    }

    public void refreshList() {
        contacts = contactRepository.getContacts();
        view.updateContacts(contacts);
    }

    public void itemSwiped(int adapterPosition) {
        view.removeFromAdapter(adapterPosition);
        contactRepository.removeContact(contacts.get(adapterPosition));
        contacts.remove(adapterPosition);
    }
}
