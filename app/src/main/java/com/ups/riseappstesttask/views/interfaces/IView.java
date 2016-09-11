package com.ups.riseappstesttask.views.interfaces;

public interface IView {

    void showProgress();
    void hideProgress();
    void showErrorMessage(String title, String body);
    String getErrorTitle(Exception exception);
}
