package com.ups.riseappstesttask.views;

import com.ups.riseappstesttask.R;
import com.ups.riseappstesttask.presenters.ContactListPresenter;
import com.ups.riseappstesttask.views.interfaces.ABaseActivityView;
import com.ups.riseappstesttask.views.interfaces.IContactListView;

public class ContactListActivity extends ABaseActivityView<ContactListPresenter> implements IContactListView {

    @Override
    protected ContactListPresenter createPresenter() {
        return new ContactListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_list;
    }
}
