package com.autocomple.config;

import java.util.Map;

public class RemoteConfig {
    private String url;
    private String wildcard;
    private Map<String, String> headers;
    private boolean jsonpEnabledAsBoolean;

    public String getUrl() {
        return url;
    }

    public String getWildcard() {
        return wildcard;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public boolean getJsonpEnabledAsBoolean() {
        return jsonpEnabledAsBoolean;
    }
}
