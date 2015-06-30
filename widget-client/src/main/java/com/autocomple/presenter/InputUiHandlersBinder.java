package com.autocomple.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

public abstract class InputUiHandlersBinder {

    public static InputUiHandlersBinder create() {
        return GWT.create(InputUiHandlersBinder.class);
    }

    protected AutocompleUiHandlers uiHandlers;

    public void bind(InputElement inputElement) {
        EventListener eventListener = createEventListener();

        Event.sinkEvents(inputElement, getEventBits());
        alsoSinkEvents(inputElement, eventListener);

        Event.setEventListener(inputElement, eventListener);
    }

    protected abstract EventListener createEventListener();

    protected abstract int getEventBits();

    protected abstract void alsoSinkEvents(InputElement inputElement, EventListener eventListener);

    public void setUiHandlers(AutocompleUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

}
