package com.ups.riseappstesttask.views.interfaces;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ups.riseappstesttask.R;
import com.ups.riseappstesttask.presenters.interfaces.IPresenter;

public abstract class ABaseActivityView<T extends IPresenter> extends AppCompatActivity implements IView {
    protected ProgressDialog progressDialog;

    protected T presenter;

    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        onViewCreated(savedInstanceState);
        presenter = createPresenter();
        presenter.init();
    }

    /**
     * just for the case if we need to get something from intent
     *
     * @param savedInstanceState
     */
    protected void onViewCreated(Bundle savedInstanceState) {
    }

    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(ABaseActivityView.this);
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            }
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public String getErrorTitle(Exception exception) {
        return getString(R.string.app_name);
    }

    @Override
    public void showErrorMessage(final String title, final String body) {
        Log.d("TAG", "showErrorMessage: " + body);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(body);
        builder.setNeutralButton(R.string.dialog_positive, null);
        builder.show();
    }

    protected void startActivityWithTransition(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_up, R.anim.slide_up_out);
    }
}
