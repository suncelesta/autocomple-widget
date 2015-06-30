package com.autocomple.remote;

import com.autocomple.config.AutocompleConfig;
import com.google.gwt.core.client.GWT;

import java.util.Map;

public class TransportFactory {
    public static Transport create(AutocompleConfig config) {
        Transport result;
        String url = config.getRemoteUrl();
        String wildcard = config.getWildcard();
        Map<String, String> headers = config.getRemoteHeaders();

        if (config.isJsonp()) {
            result = createJsonpTransport(url, wildcard);
        } else {
            result = createAjaxTransport(url, wildcard, headers);
        }

        return result;
    }

    private static JsonpTransport createJsonpTransport(String url, String wildcard) {
        return new JsonpTransport(url, wildcard);
    }

    private static AjaxTransport createAjaxTransport(String url,
                                                     String wildcard,
                                                     Map<String, String> headerJsos) {
        return new AjaxTransport(url, wildcard, headerJsos);
    }
}
