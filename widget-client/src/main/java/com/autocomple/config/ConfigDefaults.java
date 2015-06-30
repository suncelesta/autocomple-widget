package com.autocomple.config;


import static com.autocomple.common.StringUtils.EMPTY;

public class ConfigDefaults {

    private ConfigDefaults() { }

    public static Size DEFAULT_SIZE = Size.L;
    public static Theme DEFAULT_THEME = Theme.DEFAULT;
    public static int MIN_SUGGESTIONS_LIMIT = 4;
    public static int MAX_SUGGESTIONS_LIMIT = 10;
    public static int DEFAULT_SUGGESTIONS_LIMIT = MIN_SUGGESTIONS_LIMIT;
    public static String DEFAULT_PLACEHOLDER = EMPTY;
    public static boolean DEFAULT_HINT_ENABLED = true;
    public static String DEFAULT_REMOTE_WILDCARD= "%QUERY";
    public static boolean DEFAULT_REMOTE_IS_JSONP = false;
}
