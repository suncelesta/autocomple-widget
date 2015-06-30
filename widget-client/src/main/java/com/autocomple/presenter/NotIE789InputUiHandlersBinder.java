package com.autocomple.presenter;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

public class NotIE789InputUiHandlersBinder extends InputUiHandlersBinder {

    public static final int EVENT_BITS = Event.ONFOCUS | Event.ONBLUR | Event.ONKEYDOWN;

    protected int getEventBits() {
        return EVENT_BITS;
    }

    protected void alsoSinkEvents(InputElement inputElement, EventListener eventListener) {
        alsoSinkONINPUT(inputElement, eventListener);
    }

    protected EventListener createEventListener() {
        return new EventListener() {
            @Override
            public void onBrowserEvent(final Event event) {
                if (uiHandlers != null) {
                    switch (event.getType()) {
                        case "focus":
                            uiHandlers.onFocus(event);
                            break;

                        case "blur":
                            uiHandlers.onBlur(event);
                            break;

                        case "keydown":
                            uiHandlers.onKeydown(event);
                            break;

                        case "input":
                            uiHandlers.onInput(event);
                            break;

                        default:
                            break;
                    }
                }
            }
        };
    }

    public native void alsoSinkONINPUT(InputElement inputElement, EventListener eventListener) /*-{
        inputElement.oninput = function (event) {
            eventListener.@com.google.gwt.user.client.EventListener::onBrowserEvent(Lcom/google/gwt/user/client/Event;)(event);
            return true;
        }
    }-*/;
}
