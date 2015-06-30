package com.autocomple.widget;

import com.autocomple.resources.AutocompleBundle;
import com.autocomple.common.Utils;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.ConfigUtils;
import com.autocomple.config.Size;
import com.autocomple.config.Theme;
import com.autocomple.embedding.EmbeddableWidget;
import com.autocomple.presenter.AutocompleUiHandlers;
import com.autocomple.presenter.HasUiHandlers;
import com.autocomple.presenter.InputUiHandlersBinder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;

import static com.autocomple.common.Utils.*;

public class AutocompleWidget extends EmbeddableWidget<AutocompleConfig> implements
        HasUiHandlers<AutocompleUiHandlers>, HasValue<String> {

    interface AutocompleWidgetCompositeUiBinder extends UiBinder<HTMLPanel, AutocompleWidget> {

    }

    @UiField(provided = true)
    AutocompleBundle.Style css = AutocompleBundle.INSTANCE.css();

    @UiField
    HTMLPanel slot;
    @UiField
    Hintholder hintholder;// plays role of the hint and placeholder
    @UiField
    Dropdown dropdown;

    private final InputUiHandlersBinder inputUiHandlersBinder;
    private AutocompleUiHandlers uiHandlers;
    private InputElement input;
    private static AutocompleWidgetCompositeUiBinder uiBinder = GWT.create(AutocompleWidgetCompositeUiBinder.class);
    private AutocompleConfig config;

    public AutocompleWidget() {
        inputUiHandlersBinder = InputUiHandlersBinder.create();
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void embed(Element target, AutocompleConfig config) {
        this.input = target.cast();
        this.config = config;
        init();
        embed();
    }

    private void embed() {
        input.getParentElement().replaceChild(getElement(), input);
        slot.getElement().insertAfter(Node.as(input), Node.as(hintholder.getElement()));

        onAttach();
    }

    private void init() {
        hintholder.init(config);
        dropdown.init(config);
        initInput();
    }

    private void initInput() {
        initInputStyle();
        initInputAttrs();
        bindInputUiHandlers();
    }

    private void initInputStyle() {
        input.addClassName(css.autocompleInput());
        input.getStyle().setDisplay(com.google.gwt.dom.client.Style.Display.BLOCK);
        input.getStyle().setVisibility(com.google.gwt.dom.client.Style.Visibility.VISIBLE);

        Size size = config.getSize();
        input.addClassName(getInputSizeStyle(size));

        Theme theme = config.getTheme();
        input.addClassName(getInputThemeStyle(theme));
    }

    private void initInputAttrs() {
        input.setAttribute("autocomplete", "off");
        input.setAttribute("spellcheck", "false");
        input.removeAttribute(ConfigUtils.PLACEHOLDER_ATTR);
    }

    private void bindInputUiHandlers() {
        inputUiHandlersBinder.bind(input);
    }

    @Override
    public String getValue() {
        return input.getValue();
    }

    @Override
    public void setValue(String value) {
        input.setValue(value);
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

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    public SuggestionCellList getSuggestionCellList() {
        return dropdown.getSuggestionCellList();
    }

    @Override
    public void setUiHandlers(AutocompleUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
        dropdown.setUiHandlers(uiHandlers);
        inputUiHandlersBinder.setUiHandlers(uiHandlers);
    }

    @Override
    public AutocompleUiHandlers getUiHandlers() {
        return uiHandlers;
    }

    public void setFocused(boolean focused) {
        if (focused) {
            input.focus();
        } else {
            input.blur();
        }
    }

    public InputElement getTarget(){
        return input;
    }

    public void setConfig(AutocompleConfig config) {
        this.config = config;
    }

    public AutocompleConfig getConfig() {
        return config;
    }

    public void setHintholderValue(String value) {
            hintholder.setValue(value);
    }

    public boolean isInputHasOverflow(){
        return Utils.isInputHasOverflow(input);
    }

    public String getHintholderValue() {
        return hintholder.getValue();
    }

    public void setDropdownVisible(boolean visible) {
        dropdown.setVisible(visible);
    }

    public String getDefaultHintholderValue() {
        return hintholder.getDefaultValue();
    }

    public void setValueFromHintholder() {
        setValue(hintholder.getValue());
    }

    public boolean isCursorAtTheEnd() {
        return Utils.isCursorAtTheEnd(input);
    }
}
