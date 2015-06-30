package com.autocomple.common;

import com.google.gwt.core.client.GWT;


public class Logging {

    private Logging() {

    }

    private static class Level{
        public static final String WARNING = "WARNING";
        public static final String ERROR = "ERROR";
    }

    private static final String LOG_LEVEL_DELIMITER = ": ";

    public static void warning(String log) {
        StringBuilder b = new StringBuilder();

        b.append(Level.WARNING);
        b.append(LOG_LEVEL_DELIMITER);
        b.append(log);

        console(b.toString());
    }

    public static void error(String log) {
        StringBuilder b = new StringBuilder();

        b.append(Level.ERROR);
        b.append(LOG_LEVEL_DELIMITER);
        b.append(log);

        console(b.toString());
    }

    public static void console(String message) {
        GWT.log(message);
    }
}
