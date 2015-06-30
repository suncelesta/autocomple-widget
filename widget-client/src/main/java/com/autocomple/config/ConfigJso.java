package com.autocomple.config;

import com.autocomple.common.Logging;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;

public class ConfigJso extends JavaScriptObject {

    protected ConfigJso() {
    }

    public static ConfigJso create(String size, String theme,
                                   String suggestionCount, String hintholder, String hintEnabled, SourceJso sourceJso) {
        return create(null, size, theme, suggestionCount, hintholder, hintEnabled, sourceJso);
    }

    public static ConfigJso create(String size, String theme,
                                   String suggestionCount, String hintholder,
                                   String hintEnabled, String remoteUrl,
                                   String remoteWildcard, String remoteJsonpEnable) {
        return create(size, theme, suggestionCount, hintholder, hintEnabled, remoteUrl, remoteWildcard, remoteJsonpEnable, null);
    }

    public static ConfigJso create(String size, String theme,
                                   String suggestionCount, String hintholder,
                                   String hintEnabled, String remoteUrl,
                                   String remoteWildcard, String remoteJsonpEnabled, String remoteHeadersJson) {
        JsArray<HeaderJso> headerJsoJsArray = null;
        try {
            headerJsoJsArray = JsonUtils.safeEval(remoteHeadersJson);
        } catch (IllegalArgumentException e) {
            Logging.warning("Can't extract headers value. It is ignored");
        }
        RemoteJso remoteJso = RemoteJso.create(remoteUrl, remoteWildcard, remoteJsonpEnabled, headerJsoJsArray);
        SourceJso sourceJso = SourceJso.create(remoteJso);

        return create(null, size, theme, suggestionCount, hintholder, hintEnabled, sourceJso);
    }

    public static native ConfigJso create(String elementId, String size, String theme,
                                          String suggestionCount, String hintholder, String hintEnabled, SourceJso sourceJso) /*-{
        return {
            elementId: elementId,
            size: size,
            theme: theme,
            suggestionsCount: suggestionCount,
            hintholder: hintholder,
            hintEnabled: hintEnabled,
            source: sourceJso
        };
    }-*/;

    public final native String getElementId() /*-{
        return this.elementId;
    }-*/;

    public final native String getSize() /*-{
        return this.size;
    }-*/;

    public final native String getTheme() /*-{
        return this.theme;
    }-*/;

    public final native String getSuggestionsCount() /*-{
        return this.suggestionsCount;
    }-*/;

    public final native String getHintholder() /*-{
        return this.hintholder;
    }-*/;

    public final native String getHintEnabledAsString() /*-{
        return this.hintEnabled;
    }-*/;

    public final native boolean isHintEnabled() /*-{
        return this.hintEnabled;
    }-*/;

    public final native void setSize(Size size) /*-{
        this.size = size.@com.autocomple.config.Size::toString()();
    }-*/;

    public final native void setTheme(Theme theme) /*-{
        this.theme = theme.@com.autocomple.config.Theme::toString()();
    }-*/;

    public final native void setSuggestionsCount(int suggestionsCount) /*-{
        this.suggestionsCount = suggestionsCount;
    }-*/;

    public final native void setHintholder(String hintholder) /*-{
        this.hintholder = hintholder;
    }-*/;

    public final native void setHintEnabled(boolean hintEnabled) /*-{
        this.hintEnabled = hintEnabled;
    }-*/;

    public final native SourceJso getSourceJso() /*-{
        return this.source;
    }-*/;

    public final native SourceJso setSourceJso(SourceJso sourceJso) /*-{
        this.source = sourceJso;
    }-*/;
}
