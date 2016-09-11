package com.ups.riseappstesttask.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ups.riseappstesttask.R;
import com.ups.riseappstesttask.model.Extras;
import com.ups.riseappstesttask.model.repositories.RealmContactRepository;
import com.ups.riseappstesttask.presenters.ContactListPresenter;
import com.ups.riseappstesttask.views.interfaces.ABaseActivityView;
import com.ups.riseappstesttask.views.interfaces.IContactListView;

public class ContactListActivity extends ABaseActivityView<ContactListPresenter> implements IContactListView {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    @Override
    protected ContactListPresenter createPresenter() {
        return new ContactListPresenter(this, RealmContactRepository.getInstance());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_list;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        recyclerView = (RecyclerView) findViewById(R.id.rvList);
        fab = (FloatingActionButton) findViewById(R.id.fabAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, ContactDetailsActivity.class);
                intent.putExtra(Extras.EXTRA_ADD_OPERATION, true);
                startActivityWithTransition(intent);
            }
        });
    }
}
