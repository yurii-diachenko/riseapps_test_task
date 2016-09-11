package com.ups.riseappstesttask.presenters;

import com.ups.riseappstesttask.presenters.interfaces.IPresenter;
import com.ups.riseappstesttask.views.interfaces.IContactListView;

/**
 * Created by Yuriy Diachenko on 11.09.2016.
 */
public class ContactListPresenter extends IPresenter<IContactListView> {
    public ContactListPresenter(IContactListView view) {
        super(view);
    }

    @Override
    public void init() {

    }
}
