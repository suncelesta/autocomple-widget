package com.autocomple.presenter;

import com.google.gwt.user.client.Event;

public interface InputUiHandlers extends UiHandlers {
    void onInput(Event event);

    void onFocus(Event event);

    void onBlur(Event event);

    void onKeydown(Event event);
}
