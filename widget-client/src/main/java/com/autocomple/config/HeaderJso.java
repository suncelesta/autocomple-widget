package com.autocomple.config;

import com.google.gwt.core.client.JavaScriptObject;

public class HeaderJso extends JavaScriptObject {
    protected HeaderJso() {
    }

    public final native String getKey() /*-{
        return Object.keys(this)[0];
    }-*/;

    public final native String getValue() /*-{
        return this[Object.keys(this)[0]];
    }-*/;
}
