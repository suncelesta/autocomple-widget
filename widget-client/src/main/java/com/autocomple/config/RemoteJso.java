package com.autocomple.config;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

import java.util.Map;

public class RemoteJso extends JavaScriptObject {

    protected RemoteJso() {
    }

    public static native RemoteJso create(String url, String wildcard, String jsonpEnabled, JsArray<HeaderJso> headers)/*-{
        return {
            url: url,
            wildcard: wildcard,
            jsonp: jsonpEnabled,
            headers: headers
        };
    }-*/;

    public static native RemoteJso create(String url, String wildcard, String jsonpEnabled)/*-{
        return {
            url: url,
            wildcard: wildcard,
            jsonp: jsonpEnabled
        };
    }-*/;

    public final native String getJsonpEnabledAsString()/*-{
        return this.jsonp;
    }-*/;

    public final native boolean getJsonpEnabledAsBoolean()/*-{
        return this.jsonp;
    }-*/;

    public final native String getWildcard() /*-{
        return this.wildcard;
    }-*/;

    public final native String getUrl() /*-{
        return this.url;
    }-*/;

    public final native Map<String, String> getHeaders() /*-{
        return this.headers;
    }-*/;

    public final native JsArray<HeaderJso> getHeadersJsArray() /*-{
        return this.headers;
    }-*/;

    public final native String getHeadersString() /*-{
        return this.headers;
    }-*/;

    public final native void setHeadersMap(Map<String, String> headersMap) /*-{
        this.headers = headersMap;
    }-*/;

    public final native void setJsonpEnabled(boolean jsonpEnabled)/*-{
        this.jsonp = jsonpEnabled;
    }-*/;

    public final native void setWildcard(String wildcard) /*-{
        this.wildcard = wildcard;
    }-*/;

    public final native void getUrl(String url) /*-{
        this.url = url;
    }-*/;
}
