package com.autocomple.common;

public final class StringUtils {

    public static final String EMPTY = "";
    public static final int WHITE_SPACE = 0x20;
    public static final char UNASSIGNED = 0x00;

    private StringUtils() {
    }

    public static void main(String[] args) {
        System.out.println(insertSpaces("hello   ", "hello kitty"));
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isBlank(String s) {
        return (s == null || s.trim().isEmpty());
    }

    public static String stripLeadingAndCondenseWhitespaces(String str) {
        if (isEmpty(str)) {
            return str;
        }

        StringBuilder result = new StringBuilder();
        char lastCh = WHITE_SPACE, curCh;

        for (int i = 0; i < str.length(); i++) {
            curCh = str.charAt(i);

            if (!(curCh == WHITE_SPACE && lastCh == WHITE_SPACE)) {
                result.append(curCh);
            }

            lastCh = curCh;
        }

        return result.toString();
    }

    public static String cleanupHtml(String s) {
        return s.replaceAll("<[^>]*>", "");
    }

    public static String insertSpaces(String from, String to) {
        if (isEmpty(from)) {
            return to;
        }

        if (isEmpty(to)) {
            to = EMPTY;
        }

        int lengthOfFrom = from.length();
        int lengthOfTo = to.length();

        StringBuilder result = new StringBuilder();

        int t = 0;
        for (int f = 0; f < lengthOfFrom; f++) {
            char chOfFrom = from.charAt(f);
            char chOfTo = UNASSIGNED;

            if (t < lengthOfTo) {
                chOfTo = to.charAt(t);
            }

            if (chOfFrom == WHITE_SPACE && chOfTo == WHITE_SPACE) {
                t++;

            } else if (chOfFrom == WHITE_SPACE) {
                chOfTo = WHITE_SPACE;

            } else {
                t++;
            }

            if (chOfTo != UNASSIGNED) {
                result.append(chOfTo);
            }
        }

        while (t < lengthOfTo) {
            result.append(to.charAt(t));
            t++;
        }

        return result.toString();
    }

    public static String adjustLetterCase(String of, String by) {
        StringBuilder result = new StringBuilder();
        int lengthOfBy = by.length();
        int lengthOfOf = of.length();

        int i = 0;
        for (; i < lengthOfBy && i < lengthOfOf; i++) {
            char chOfFrom = by.charAt(i);
            char chOfOf = of.charAt(i);

            if (chOfFrom != chOfOf &&
                    Character.toLowerCase(chOfFrom) == Character.toLowerCase(chOfOf)) {
                result.append(chOfFrom);

            } else {
                result.append(chOfOf);
            }
        }

        for (; i < lengthOfOf; i++) {
            result.append(of.charAt(i));
        }

        return result.toString();
    }

    public static String extractDifferentTokenTillWhiteSpace(String from, String comparable) {
        StringBuilder result = new StringBuilder();
        int lengthOfFrom = from.length();
        int lengthOfComparable = comparable.length();
        boolean isSomeExtracted = false;

        for (int i = 0; i < lengthOfFrom; i++) {
            char chOfFrom = from.charAt(i);
            char chOfComparable = UNASSIGNED;

            if (i < lengthOfComparable) {
                chOfComparable = comparable.charAt(i);
            }

            if (chOfFrom != chOfComparable) {
                if (chOfFrom == WHITE_SPACE) {
                    if (isSomeExtracted) {
                        break;
                    }

                } else {
                    isSomeExtracted = true;
                }

                result.append(chOfFrom);
            }
        }

        return isSomeExtracted ? result.toString() : EMPTY;
    }
}
