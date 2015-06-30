package com.autocomple.config;


import java.util.Map;

public class AutocompleConfig {
    private Size size = ConfigDefaults.DEFAULT_SIZE;
    private Theme theme = ConfigDefaults.DEFAULT_THEME;
    private int suggestionsLimit = ConfigDefaults.DEFAULT_SUGGESTIONS_LIMIT;
    private boolean hintEnabled = ConfigDefaults.DEFAULT_HINT_ENABLED;
    private String wildcard = ConfigDefaults.DEFAULT_REMOTE_WILDCARD;
    private boolean isJsonp = ConfigDefaults.DEFAULT_REMOTE_IS_JSONP;
    private String placeholder = ConfigDefaults.DEFAULT_PLACEHOLDER;
    private String remoteUrl;
    private Map<String, String> remoteHeaders;

    public Size getSize() {
        return size;
    }

    public Theme getTheme() {
        return theme;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void setSuggestionsLimit(int suggestionsLimit) {
        this.suggestionsLimit = suggestionsLimit;
    }

    public void setHintEnabled(boolean hintEnabled) {
        this.hintEnabled = hintEnabled;
    }

    public boolean isHintEnabled() {
        return hintEnabled;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setWildcard(String wildcard) {
        this.wildcard = wildcard;
    }

    public String getWildcard() {
        return wildcard;
    }

    public void setIsJsonp(boolean isJsonp) {
        this.isJsonp = isJsonp;
    }

    public boolean isJsonp() {
        return isJsonp;
    }

    public void setJsonp(boolean isJsonp) {
        this.isJsonp = isJsonp;
    }

    public int getSuggestionsLimit() {
        return suggestionsLimit;
    }

    public void setRemoteHeaders(Map<String, String> remoteHeaders) {
        this.remoteHeaders = remoteHeaders;
    }

    public Map<String, String> getRemoteHeaders() {
        return remoteHeaders;
    }
}
