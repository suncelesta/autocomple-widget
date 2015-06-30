package com.autocomple.config;

import com.google.gwt.core.client.JavaScriptObject;

public class SourceJso extends JavaScriptObject {
    protected SourceJso() {
    }

    public static native SourceJso create(RemoteJso remoteJso)/*-{
        return {
                remote: remoteJso
        };
    }-*/;

    public final native RemoteJso getRemoteJso() /*-{
        return this.remote;
    }-*/;

    public final native SourceJso setRemoteJso(RemoteJso remoteJso) /*-{
        this.remote = remoteJso;
    }-*/;

}
