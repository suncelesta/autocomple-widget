package com.autocomple.widget;

import com.autocomple.resources.AutocompleBundle;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Size;
import com.autocomple.config.Theme;
import com.autocomple.presenter.DropdownUiHandlers;
import com.autocomple.presenter.HasUiHandlers;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import static com.autocomple.common.Utils.getSizeStyle;
import static com.autocomple.common.Utils.getThemeStyle;

class Dropdown extends Composite implements HasUiHandlers<DropdownUiHandlers> {

    private DropdownUiHandlers uiHandlers;

    interface AutocompleContainerUiBinder extends UiBinder<Widget, Dropdown> {

    }

    @UiField(provided = true)
    AutocompleBundle.Style css = AutocompleBundle.INSTANCE.css();

    @UiField
    HTMLPanel dropdown;
    @UiField
    SuggestionCellList suggestionCellList;

    private static AutocompleContainerUiBinder uiBinder = GWT.create(AutocompleContainerUiBinder.class);

    public Dropdown() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void init(AutocompleConfig config) {
        Size size = config.getSize();
        dropdown.getElement().addClassName(getSizeStyle(size));

        Theme theme = config.getTheme();
        dropdown.getElement().addClassName(getThemeStyle(theme));
    }

    @Override
    public DropdownUiHandlers getUiHandlers() {
        return uiHandlers;
    }

    @Override
    public void setUiHandlers(DropdownUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
        suggestionCellList.setUiHandlers(uiHandlers);
    }


    public SuggestionCellList getSuggestionCellList() {
        return suggestionCellList;
    }

    public boolean isVisible() {
        return dropdown.isVisible();
    }

}
