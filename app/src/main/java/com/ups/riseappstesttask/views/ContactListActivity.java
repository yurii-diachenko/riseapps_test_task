package com.ups.riseappstesttask.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.ups.riseappstesttask.R;
import com.ups.riseappstesttask.model.Extras;
import com.ups.riseappstesttask.model.entities.Contact;
import com.ups.riseappstesttask.model.repositories.RealmContactRepository;
import com.ups.riseappstesttask.presenters.ContactListPresenter;
import com.ups.riseappstesttask.views.adapters.ContactsAdapter;
import com.ups.riseappstesttask.views.interfaces.ABaseActivityView;
import com.ups.riseappstesttask.views.interfaces.IContactListView;

import java.util.List;

public class ContactListActivity extends ABaseActivityView<ContactListPresenter> implements IContactListView {

    public static final int REQUEST_CODE = 777;

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ContactsAdapter adapter;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                presenter.itemSwiped(viewHolder.getLayoutPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        fab = (FloatingActionButton) findViewById(R.id.fabAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, ContactDetailsActivity.class);
                intent.putExtra(Extras.EXTRA_ADD_OPERATION, true);
                startActivityWithTransition(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        adapter = new ContactsAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateContacts(List<Contact> contacts) {
        adapter.update(contacts);
    }

    @Override
    public void removeFromAdapter(int adapterPosition) {
        adapter.remove(adapterPosition);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            presenter.refreshList();
        }
    }
}
