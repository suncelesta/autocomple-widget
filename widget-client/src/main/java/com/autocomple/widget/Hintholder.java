package com.autocomple.widget;

import com.autocomple.resources.AutocompleBundle;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Size;
import com.autocomple.config.Theme;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

import static com.autocomple.common.StringUtils.EMPTY;
import static com.autocomple.common.Utils.getHintholderSizeStyle;
import static com.autocomple.common.Utils.getHintholderThemeStyle;

class Hintholder extends Widget implements HasValue<String> {

    private boolean isHintEnabled;
    private String defaultValue;

    interface AutocompleContainerUiBinder extends UiBinder<InputElement, Hintholder> {
    }

    @UiField(provided = true)
    AutocompleBundle.Style css = AutocompleBundle.INSTANCE.css();
    @UiField
    InputElement input;

    private static AutocompleContainerUiBinder uiBinder =
            GWT.create(AutocompleContainerUiBinder.class);

    public Hintholder() {
        setElement(uiBinder.createAndBindUi(this));
    }


    public void init(AutocompleConfig config) {
        // TODO: make getHintEnabledAsString result type boolean
        isHintEnabled = config.isHintEnabled();
        defaultValue = config.getPlaceholder();
        Size size = config.getSize();
        Theme theme = config.getTheme();

        input.addClassName(getHintholderSizeStyle(size));
        input.addClassName(getHintholderThemeStyle(theme));

        resetDefaultValue();
    }

    @Override
    public String getValue() {
        return input.getValue();
    }

    @Override
    public void setValue(String value) {
        input.setValue(isHintEnabled ? value : EMPTY);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        String oldValue = fireEvents ? getValue() : null;
        setValue(value);

        if (fireEvents) {
            String newValue = getValue();
            ValueChangeEvent.fireIfNotEqual(this, oldValue, newValue);
        }
    }

    public void resetDefaultValue() {
        setValue(defaultValue);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
