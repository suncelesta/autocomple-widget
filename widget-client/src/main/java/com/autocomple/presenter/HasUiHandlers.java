package com.autocomple.presenter;

public interface HasUiHandlers<T extends UiHandlers> {
    T getUiHandlers();

    void setUiHandlers(T uiHandlers);
}
