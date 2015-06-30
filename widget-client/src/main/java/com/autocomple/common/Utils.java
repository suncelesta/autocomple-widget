package com.autocomple.common;

import com.autocomple.resources.AutocompleBundle;
import com.autocomple.config.Size;
import com.autocomple.config.Theme;
import com.autocomple.data.SuggestionJso;
import com.google.gwt.dom.client.*;

import java.util.HashMap;
import java.util.Map;

import static com.autocomple.common.StringUtils.EMPTY;

public class Utils {

    public static final SuggestionJso EMPTY_SUGGESTION_JSO = SuggestionJso.create(EMPTY);

    public static final AutocompleBundle.Style CSS = AutocompleBundle.INSTANCE.css();

    private Utils() {
    }

    public static boolean isEventWithModifier(NativeEvent nativeEvent) {
        boolean altKey = nativeEvent.getAltKey();
        boolean ctrlKey = nativeEvent.getCtrlKey();
        boolean shiftKey = nativeEvent.getShiftKey();
        boolean metaKey = nativeEvent.getMetaKey();

        return altKey || ctrlKey || shiftKey || metaKey;
    }

    public static String getInputSizeStyle(Size size) {
        switch (size) {
            case S:
                return CSS.autocompleInputSm();
            case L:
                return CSS.autocompleInputLg();
            default:
                return CSS.autocompleInput();
        }
    }

    public static String getInputThemeStyle(Theme theme) {
        switch (theme) {
            case DARK:
                return CSS.autocompleInputDark();
            case CREME:
                return CSS.autocompleInputCreme();
            default:
                return CSS.autocompleInput();
        }
    }

    public static String getSizeStyle(Size size) {
        switch (size) {
            case S:
                return CSS.autocompleDropdownSm();
            case L:
                return CSS.autocompleDropdownLg();
            default:
                return CSS.autocompleDropdown();
        }
    }

    public static String getThemeStyle(Theme theme) {
        switch (theme) {
            case DARK:
                return CSS.autocompleDropdownDark();
            case CREME:
                return CSS.autocompleDropdownCreme();
            default:
                return CSS.autocompleDropdown();
        }
    }

    public static String getHintholderSizeStyle(Size size) {
        switch (size) {
            case S:
                return CSS.autocompleHintholderSm();
            case L:
                return CSS.autocompleHintholderLg();
            default:
                return CSS.autocompleHintholder();
        }
    }

    public static String getHintholderThemeStyle(Theme theme) {
        switch (theme) {
            case DARK:
                return CSS.autocompleHintholderDark();
            case CREME:
                return CSS.autocompleHintholderCreme();
            default:
                return CSS.autocompleHintholder();
        }
    }

    public static boolean isCursorAtTheEnd(InputElement element) {
        String value = element.getValue();

        return value.length() == getCursorPos(element);
    }

    public static NodeList<Element> getInputElements() {
        return Document.get().getElementsByTagName(InputElement.TAG);
    }

    public static native int getCursorPos(Element elem) /*-{
        try {
            return elem.selectionStart;
        } catch (e) {
            return 0;
        }
    }-*/;

    public static native String getComputedStyleProperty(Element el, String prop)  /*-{
        var computedStyle;
        if (document.defaultView && document.defaultView.getComputedStyle) { // standard (includes ie9)
            computedStyle = document.defaultView.getComputedStyle(el, null)[prop];

        } else if (el.currentStyle) { // IE older
            computedStyle = el.currentStyle[prop];

        } else { // inline style
            computedStyle = el.style[prop];
        }
        return computedStyle;
    }-*/;

    public static double getSumOfHorizontalPaddings(Element element){
        String paddingRight = getComputedStyleProperty(element, "paddingRight");
        String paddingLeft = getComputedStyleProperty(element, "paddingLeft");
        return pixelValueToDouble(paddingLeft) + pixelValueToDouble(paddingRight);
    }

    /**
     *
     * @exception NumberFormatException  if the string does not contain a
     *               parsable double.
     */
    private static double pixelValueToDouble(String pixelValue){
        String PIXEL_SUFFIX  = "px";
        return Double.parseDouble(pixelValue.substring(0, pixelValue.length() - PIXEL_SUFFIX.length()));
    }

    public static Map<String, String> jsoToMap(Jso jso) {
        Map<String, String> result = new HashMap<>();

        for (int i = 0; i < jso.getKeysCount(); i++) {
            String key = jso.getKey(i);
            result.put(key, jso.getString(key));
        }

        return result;
    }

    public static boolean isInputHasOverflow(Element input){
        double horizontalOffset = Utils.getSumOfHorizontalPaddings(input);
        return (input.getScrollWidth() > (input.getClientWidth() - horizontalOffset));
    }

    public static native void fireEvent(String name, Element target)/*-{
        var evt = document.createEvent("Event");
        evt.initEvent(name, true, true);
        target.dispatchEvent(evt);
    }-*/;
}
