package com.ups.riseappstesttask.presenters;

import android.text.TextUtils;

import com.ups.riseappstesttask.model.entities.Contact;
import com.ups.riseappstesttask.model.repositories.interfaces.IContactRepository;
import com.ups.riseappstesttask.presenters.interfaces.IPresenter;
import com.ups.riseappstesttask.views.interfaces.IContactDetailsView;

/**
 * Created by Yuriy Diachenko on 11.09.2016.
 */
public class ContactDetailsPresenter extends IPresenter<IContactDetailsView> {
    private boolean isAddOperation;
    private IContactRepository contactRepository;

    public ContactDetailsPresenter(IContactDetailsView view, boolean isAddOperation, IContactRepository contactRepository) {
        super(view);
        this.isAddOperation = isAddOperation;
        this.contactRepository = contactRepository;
    }

    @Override
    public void init() {
        view.setTitle(isAddOperation ? "New" : "Edit");

    }

    public boolean validateFormAndSave(String firstName, String secondName, String surname, String email, String phone, int imageIndex) {
        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(secondName) ||
                TextUtils.isEmpty(surname) || TextUtils.isEmpty( email) || TextUtils.isEmpty(phone)) {
            return false;
        } else {
            Contact contact = new Contact();
            contact.setFirstName(firstName);
            contact.setSecondName(secondName);
            contact.setSurname(surname);
            contact.setPhone(phone);
            contact.setEmail(email);
            contact.setImage(imageIndex);

            contactRepository.addContact(contact);
            return true;
        }
    }
}
