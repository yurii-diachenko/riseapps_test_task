package com.ups.riseappstesttask.presenters;

import com.ups.riseappstesttask.model.repositories.interfaces.IContactRepository;
import com.ups.riseappstesttask.presenters.interfaces.IPresenter;
import com.ups.riseappstesttask.views.interfaces.IContactListView;

/**
 * Created by Yuriy Diachenko on 11.09.2016.
 */
public class ContactListPresenter extends IPresenter<IContactListView> {
    private IContactRepository contactRepository;

    public ContactListPresenter(IContactListView view, IContactRepository contactRepository) {
        super(view);
        this.contactRepository = contactRepository;
    }

    @Override
    public void init() {

    }
}
