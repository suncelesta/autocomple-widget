package com.autocomple.presenter;

import com.google.gwt.user.client.Event;

import static com.google.gwt.event.dom.client.KeyCodes.*;

class KeydownHandlerSwitchUtil {

    private KeydownHandlerSwitchUtil() {

    }

    static void switchKeydownEventHandler(AutocomplePresenter autocomplePresenter, Event event) {
        int keyCode = event.getKeyCode();

        switch (keyCode) {
            case KEY_ENTER:
            case KEY_MAC_ENTER:
                autocomplePresenter.onKeyboardEnter(event);
                break;

            case KEY_ESCAPE:
                autocomplePresenter.onKeyboardEscape();
                break;

            case KEY_TAB:
                autocomplePresenter.onKeyboardTab(event);
                break;

            case KEY_RIGHT:
                autocomplePresenter.onKeyboardRight(event);
                break;

            case KEY_UP:
                autocomplePresenter.onKeyboardUp(event);
                break;

            case KEY_DOWN:
                autocomplePresenter.onKeyboardDown(event);
                break;

            default:
                break;

        }
    }

}
