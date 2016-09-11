package com.ups.riseappstesttask.presenters.interfaces;

import com.ups.riseappstesttask.views.interfaces.IView;

public abstract class IPresenter<T extends IView> {
    protected T view;

    public IPresenter(T view) {
        this.view = view;
    }

    public abstract void init();
}
