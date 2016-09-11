package com.ups.riseappstesttask.views;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ups.riseappstesttask.R;
import com.ups.riseappstesttask.model.Extras;
import com.ups.riseappstesttask.model.repositories.RealmContactRepository;
import com.ups.riseappstesttask.presenters.ContactDetailsPresenter;
import com.ups.riseappstesttask.views.interfaces.ABaseActivityView;
import com.ups.riseappstesttask.views.interfaces.IContactDetailsView;

public class ContactDetailsActivity extends ABaseActivityView<ContactDetailsPresenter> implements IContactDetailsView {

    private boolean addOperation;
    private TextView title;
    private Button btnDone;
    private EditText etFirstName;
    private EditText etSecondName;
    private EditText etSurname;
    private EditText etEmail;
    private EditText etPhone;
    private LinearLayout container;




    @Override
    protected ContactDetailsPresenter createPresenter() {
        return new ContactDetailsPresenter(this, addOperation, RealmContactRepository.getInstance());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_details;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addOperation = getIntent().getBooleanExtra(Extras.EXTRA_ADD_OPERATION, false);

        title = (TextView) findViewById(R.id.tvContactDetailsTitle);
        btnDone = (Button) findViewById(R.id.btnDone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etSecondName = (EditText) findViewById(R.id.etSecondName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etPhone = (EditText) findViewById(R.id.etPhone);
        container = (LinearLayout) findViewById(R.id.lvImageContainer);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!presenter.validateFormAndSave(etFirstName.getText().toString(),
                        etSecondName.getText().toString(),
                        etSurname.getText().toString(),
                        etEmail.getText().toString(),
                        etPhone.getText().toString(),
                        getImageIndex())) {
                    showErrorMessage(getString(R.string.app_name), getString(R.string.fill_all_fields));
                } else {
                    setResult(RESULT_OK);
                    finish();
                    overridePendingTransition(R.anim.slide_down_in, R.anim.slide_down);
                }
            }
        });

        setupImageSelector();
    }

    private void setupImageSelector() {
        for (int i = 0; i < container.getChildCount(); i++) {
            container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearImageSelection();
                    ((ImageView)((FrameLayout)v).getChildAt(0)).setImageResource(R.drawable.ic_check_white_24dp);
                }
            });
        }
    }

    private void clearImageSelection() {
        for (int i = 0; i < container.getChildCount(); i++) {
            ((ImageView)((FrameLayout)container.getChildAt(i)).getChildAt(0)).setImageDrawable(null);
        }
    }

    private int getImageIndex() {
        for (int i = 0; i < container.getChildCount(); i++) {
            if (((ImageView)((FrameLayout)container.getChildAt(i)).getChildAt(0)).getDrawable() != null) {
                return i;
            }
        }

        return 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }
}
