package com.autocomple.widget;

import com.autocomple.data.SuggestionJso;
import com.autocomple.presenter.DropdownUiHandlers;
import com.autocomple.presenter.HasUiHandlers;
import com.autocomple.resources.AutocompleBundle;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiRenderer;

import static com.google.gwt.safehtml.shared.SafeHtmlUtils.fromTrustedString;

class SuggestionCell extends AbstractCell<SuggestionJso> implements HasUiHandlers<DropdownUiHandlers> {

    interface SuggestionCellUiRenderer extends UiRenderer {
        void render(SafeHtmlBuilder sb,
                    SafeHtml suggestionTemplate,
                    AutocompleBundle.Style css);

        void onBrowserEvent(SuggestionCell cell,
                            NativeEvent event,
                            Element parent,
                            SuggestionJso suggestion);
    }

    private static final SuggestionCellUiRenderer renderer = GWT.create(SuggestionCellUiRenderer.class);
    private static final AutocompleBundle.Style CSS = AutocompleBundle.INSTANCE.css();
    private DropdownUiHandlers uiHandlers;

    SuggestionCell() {
        super("mouseover", "mouseout", "click");
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, SuggestionJso value, NativeEvent event, ValueUpdater<SuggestionJso> valueUpdater) {
        renderer.onBrowserEvent(this, event, parent, value);
    }

    @Override
    public void render(Context context, SuggestionJso value, SafeHtmlBuilder sb) {
        SafeHtml suggestionTemplate = getSafeHtmlSuggestionTemplate(value);

        renderer.render(sb, suggestionTemplate, CSS);
    }

    @Override
    public DropdownUiHandlers getUiHandlers() {
        return uiHandlers;
    }

    @Override
    public void setUiHandlers(DropdownUiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

    private SafeHtml getSafeHtmlSuggestionTemplate(SuggestionJso value) {
        return fromTrustedString(value.getSuggestion());
    }

    @UiHandler({"suggestionDiv"})
    void onSuggestionClicked(ClickEvent event, Element parent, SuggestionJso suggestionJso) {
        if (uiHandlers != null) {
            uiHandlers.onSuggestionClicked(event, parent, suggestionJso);
        }
    }

    @UiHandler({"suggestionDiv"})
    void onSuggestionMouseOver(MouseOverEvent event, Element parent, SuggestionJso suggestionJso) {
        if (uiHandlers != null) {
            uiHandlers.onSuggestionMouseOver(event, parent, suggestionJso);
        }
    }

    @UiHandler({"suggestionDiv"})
    void onSuggestionMouseOut(MouseOutEvent event, Element parent, SuggestionJso suggestionJso) {
        if (uiHandlers != null) {
            uiHandlers.onSuggestionMouseOut(event, parent, suggestionJso);
        }
    }
}