package com.autocomple.common;

import com.google.gwt.core.client.JavaScriptObject;

public class Jso extends JavaScriptObject{
    protected Jso() {
    }

    public final native String getString(String key)/*-{
        return this[key];
    }-*/;

    public final native Jso getJso(String key) /*-{
        return this[key];
    }-*/;

    public final native int getKeysCount() /*-{
        return Object.keys(this).length;
    }-*/;

    public final native String getKey(int index) /*-{
        return Object.keys(this)[index];
    }-*/;

}
