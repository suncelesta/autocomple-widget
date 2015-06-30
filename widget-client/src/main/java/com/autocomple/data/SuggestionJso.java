package com.autocomple.data;

import com.autocomple.Suggestion;
import com.google.gwt.core.client.JavaScriptObject;

public class SuggestionJso extends JavaScriptObject implements Suggestion {

    protected SuggestionJso() {
    }

    public static native SuggestionJso create(String suggestion) /*-{
        return {s: suggestion};
    }-*/;

    public static native SuggestionJso create(String suggestion, String x) /*-{
        return {s: suggestion, x: x};
    }-*/;

    public final native String getSuggestion() /*-{
        return this.s;
    }-*/;

    public final native String getX() /*-{
        return this.x;
    }-*/;

}
