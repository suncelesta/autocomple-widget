package com.autocomple.config;

import com.autocomple.common.Jso;
import com.google.gwt.core.client.JsArray;


public class ConfigUtils {

    public static final String PLACEHOLDER_ATTR = "placeholder";

    private ConfigUtils() {
    }

    public static native JsArray<Jso> getAutocompleConfigJsos()/*-{
        var result = [];

        var autocomple = $wnd.autocomple;

        if (autocomple && Object.prototype.toString.call(autocomple) === '[object Array]') {
            result = autocomple;
        }

        return result;
    }-*/;
}
