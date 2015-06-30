package com.autocomple.mvp;

public abstract class Presenter {

    private final View view;

    public Presenter(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }
}
