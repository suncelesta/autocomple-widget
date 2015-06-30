package com.autocomple.presenter;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

public class IE789InputUiHandlersBinder extends InputUiHandlersBinder {

    public static final int EVENT_BITS = Event.ONFOCUS | Event.ONBLUR | Event.ONPASTE | Event.ONKEYDOWN;

    protected int getEventBits() {
        return EVENT_BITS;
    }

    protected void alsoSinkEvents(InputElement inputElement, EventListener eventListener) {
        alsoSinkONCUT(inputElement, eventListener);
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
                            if (isSpecKeyCode(event.getKeyCode())) {
                                uiHandlers.onKeydown(event);
                            } else {
                                deferredOnInput(event);
                            }
                            break;

                        case "cut":
                        case "paste":
                            deferredOnInput(event);
                            break;

                        default:
                            break;
                    }
                }
            }

            private void deferredOnInput(final Event event) {
                Scheduler.get().scheduleDeferred(new Command() {
                    @Override
                    public void execute() {
                        uiHandlers.onInput(event);
                    }
                });
            }
        };
    }

    private boolean isSpecKeyCode(int keyCode) {
        return keyCode == KeyCodes.KEY_TAB ||
                keyCode == KeyCodes.KEY_ESCAPE ||
                keyCode == KeyCodes.KEY_RIGHT ||
                keyCode == KeyCodes.KEY_LEFT ||
                keyCode == KeyCodes.KEY_UP ||
                keyCode == KeyCodes.KEY_DOWN ||
                keyCode == KeyCodes.KEY_MAC_ENTER ||
                keyCode == KeyCodes.KEY_ENTER;
    }

    private static native void alsoSinkONCUT(Element inputElement, EventListener eventListener) /*-{
        inputElement.oncut = function (event) {
            eventListener.@com.google.gwt.user.client.EventListener::onBrowserEvent(Lcom/google/gwt/user/client/Event;)(event);
            return true;
        }
    }-*/;

}
