package com.autocomple.presenter;

import com.autocomple.common.Logging;
import com.autocomple.data.SuggestionJso;
import com.autocomple.data.SuggestionListDataProvider;
import com.autocomple.mvp.Presenter;
import com.autocomple.remote.Transport;
import com.autocomple.remote.TransportFactory;
import com.autocomple.widget.AutocompleWidget;
import com.autocomple.widget.SuggestionCellList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;

import static com.autocomple.common.StringUtils.*;
import static com.autocomple.common.Utils.fireEvent;
import static com.autocomple.common.Utils.isEventWithModifier;
import static com.autocomple.presenter.KeydownHandlerSwitchUtil.switchKeydownEventHandler;


final public class AutocomplePresenter extends Presenter implements AutocompleUiHandlers {

    private abstract class State {
        void onInput() {
        }

        void onSuggestionClicked(SuggestionJso suggestionJso) {
        }

        void onFocus() {
        }

        void onBlur() {
        }

        void onKeyboardUp(Event event) {
        }

        void onKeyboardDown(Event event) {
        }

        void onKeyboardTab(Event event) {
        }

        void onKeyboardEscape() {
            autocompleWidget.setFocused(false);
        }

        void onKeyboardRight(Event event) {
        }

        void onKeyboardEnter(Event event) {
        }


        public void onSuggestionMouseOver(SuggestionJso suggestionJso) {
            suggestionElementHighlighter.highlight(suggestionJso);
        }

        public void onSuggestionMouseOut() {
            suggestionElementHighlighter.resetHighlighting();
        }
    }

    private class EmptyValueClosedDropdown extends State {
        @Override
        void onInput() {
            String value = autocompleWidget.getValue();

            if (!isBlank(value)) {
                currentState = notEmptyAndClosed;
                currentState.onInput();

            } else if (isEmpty(value)) {
                setHintValue(autocompleWidget.getDefaultHintholderValue());

            } else if (isBlank(value)) {
                setHintValue(EMPTY);
            }
        }
    }

    private abstract class NotEmptyValueState extends State {
        @Override
        void onInput() {
            String widgetValue = autocompleWidget.getValue();
            String valueWithCondensedSpaces = stripLeadingAndCondenseWhitespaces(widgetValue);

            updateHintholderView();

            if (!(autocompleWidget.getHintholderValue().startsWith(widgetValue))) {
                setHintValue(EMPTY);
            }

            if (!valueWithCondensedSpaces.equals(normalizedWidgetValue)) {
                normalizedWidgetValue = valueWithCondensedSpaces;

                onAutocompleValueChanged();
            }
        }

        protected abstract void onAutocompleValueChanged();

        protected abstract void updateState(boolean hasSuggestions);

        void updateSuggestionsAndDropdown() {
            suggestionListDataProvider.getSuggestions(normalizedWidgetValue, new AsyncCallback<JsArray<SuggestionJso>>() {
                @Override
                public void onFailure(Throwable caught) {
                    Logging.error(caught.getMessage());
                }

                @Override
                public void onSuccess(JsArray<SuggestionJso> suggestions) {
                    boolean hasSuggestions = suggestions.length() > 0;
                    autocompleWidget.setDropdownVisible(hasSuggestions);

                    SuggestionJso suggestionJso = suggestionListDataProvider.getTopSuggestion();
                    setHintValue(suggestionJso);

                    updateState(hasSuggestions);
                }
            });
        }
    }

    private final class NotEmptyValueAndClosedDropdown extends NotEmptyValueState {

        @Override
        protected void onAutocompleValueChanged() {
            if (isEmpty(normalizedWidgetValue)) {
                transport.cancelPendingRequests();

                currentState = emptyAndClosed;

                currentState.onInput();
            } else {
                updateSuggestionsAndDropdown();
            }
        }

        @Override
        protected void updateState(boolean hasSuggestions) {
            currentState = hasSuggestions ? notEmptyAndOpenedDropdown : currentState;
        }

        @Override
        void onKeyboardDown(Event event) {
            if (!isEventWithModifier(event)) {
                event.preventDefault();

                updateSuggestionsAndDropdown();
            }
        }
    }

    private final class NotEmptyValueAndOpenedDropdown extends NotEmptyValueState {

        @Override
        protected void onAutocompleValueChanged() {
            if (isEmpty(normalizedWidgetValue)) {
                closeAndClearSuggestions();

                currentState = emptyAndClosed;
                currentState.onInput();

            } else {
                suggestionElementHighlighter.resetHighlighting();

                updateSuggestionsAndDropdown();
            }
        }

        @Override
        protected void updateState(boolean hasSuggestions) {
            currentState = hasSuggestions ? currentState : notEmptyAndClosed;
        }


        @Override
        void onBlur() {
            closeAndClearSuggestions();

            setHintValue(EMPTY);

            currentState = notEmptyAndClosed;
        }

        private void closeAndClearSuggestions() {
            transport.cancelPendingRequests();

            autocompleWidget.setDropdownVisible(false);

            suggestionListDataProvider.clear();

            suggestionElementHighlighter.resetHighlighting();// todo: is it pertinently?
        }

        @Override
        void onKeyboardDown(Event event) {
            if (!isEventWithModifier(event)) {
                event.preventDefault();
                onKeyboardDown();
            }
        }

        private void onKeyboardDown() {
            highlightNextSuggestion();

            if (isSomeSuggestionHighlighted()) {
                String suggestion = getHighlightedSuggestion().getSuggestion();
                autocompleWidget.setValue(normalizeSuggestion(suggestion));
                setHintValue(EMPTY);
            } else {
                resetAutocompleWidgetValue();
                resetHintholderValue();
            }
        }

        private void highlightNextSuggestion() {
            suggestionElementHighlighter.highlightNext();
        }

        @Override
        void onKeyboardUp(Event event) {
            if (!isEventWithModifier(event)) {
                event.preventDefault();
                onKeyboardUp();
            }
        }

        private void onKeyboardUp() {
            highlightPreviousSuggestion();

            if (isSomeSuggestionHighlighted()) {
                String suggestion = getHighlightedSuggestion().getSuggestion();
                autocompleWidget.setValue(normalizeSuggestion(suggestion));
                setHintValue(EMPTY);

            } else {
                resetAutocompleWidgetValue();
                resetHintholderValue();
            }
        }

        private boolean isSomeSuggestionHighlighted() {
            return suggestionElementHighlighter.isHighlighted();
        }

        private void highlightPreviousSuggestion() {
            suggestionElementHighlighter.highlightPrevious();
        }

        private void resetAutocompleWidgetValue() {
            autocompleWidget.setValue(normalizedWidgetValue);
        }

        private void resetHintholderValue() {
            if (isSomeSuggestionHighlighted()) {
                setHintValue(getHighlightedSuggestion());
            } else {
                setHintValue(suggestionListDataProvider.getTopSuggestion());
            }
        }

        @Override
        void onKeyboardTab(Event event) {
            if (!isEventWithModifier(event)) {
                if (!isWidgetValueCompleted()) {
                    event.preventDefault();

                    String suggestion = suggestionListDataProvider.getTopSuggestion().getSuggestion();
                    autocompleWidget.setValue(normalizeSuggestion(suggestion));

                    onInput();
                }
            }
        }

        private boolean isWidgetValueCompleted() {
            String widgetValue = autocompleWidget.getValue();
            String hintholderValue = autocompleWidget.getHintholderValue();

            return hintholderValue.equals(widgetValue);
        }

        @Override
        void onKeyboardEnter(Event event) {
            if (isSomeSuggestionHighlighted()) {
                event.preventDefault();

                select(getHighlightedSuggestion());
            }

            setHintValue(EMPTY);

            closeAndClearSuggestions();

            currentState = notEmptyAndClosed;
        }

        @Override
        void onSuggestionClicked(SuggestionJso suggestionJso) {
            select(suggestionJso);

            closeAndClearSuggestions();

            currentState = notEmptyAndClosed;
        }

        private void select(SuggestionJso suggestionJso) {
            String suggestion = suggestionJso.getSuggestion();
            normalizedWidgetValue = normalizeSuggestion(suggestion);
            resetAutocompleWidgetValue();
            setHintValue(EMPTY);
        }

        private String normalizeSuggestion(String suggestion) {
            return cleanupHtml(stripLeadingAndCondenseWhitespaces(suggestion));
        }

        @Override
        void onKeyboardRight(Event event) {
            if (!isEventWithModifier(event)) {
                if (autocompleWidget.isCursorAtTheEnd()) {
                    if (!isWidgetValueCompleted()) {
                        event.preventDefault();
                        autocompleteByOneToken();

                        onInput();
                    }
                }
            }
        }

        private void autocompleteByOneToken() {
            String inputValue = autocompleWidget.getValue();
            String hintholderValue = autocompleWidget.getHintholderValue();
            String nextToken = extractDifferentTokenTillWhiteSpace(hintholderValue, inputValue);

            autocompleWidget.setValue(stripLeadingAndCondenseWhitespaces((inputValue + nextToken)));
        }
    }

    private State currentState;
    private final EmptyValueClosedDropdown emptyAndClosed = new EmptyValueClosedDropdown();
    private final NotEmptyValueAndClosedDropdown notEmptyAndClosed = new NotEmptyValueAndClosedDropdown();
    private final NotEmptyValueAndOpenedDropdown notEmptyAndOpenedDropdown = new NotEmptyValueAndOpenedDropdown();

    private final AutocompleWidget autocompleWidget;
    private final SuggestionListDataProvider suggestionListDataProvider;
    private final SuggestionElementHighlighter suggestionElementHighlighter;
    private String normalizedWidgetValue;
    private String hintValue;
    private Transport transport;

    public AutocomplePresenter(AutocompleWidget autocompleWidget) {
        super(autocompleWidget);
        this.autocompleWidget = autocompleWidget;
        autocompleWidget.setUiHandlers(this);

        SuggestionCellList suggestionCellList = autocompleWidget.getSuggestionCellList();
        this.transport = TransportFactory.create(autocompleWidget.getConfig());
        int suggestionsLimit = autocompleWidget.getConfig().getSuggestionsLimit();
        this.suggestionListDataProvider = new SuggestionListDataProvider(transport, suggestionsLimit);
        this.suggestionListDataProvider.addDataDisplay(suggestionCellList);

        suggestionElementHighlighter = new SuggestionElementHighlighter(
                suggestionCellList.getSelectionModel(), suggestionListDataProvider);

        String inputValue = autocompleWidget.getValue();

        this.currentState = isBlank(inputValue) ? emptyAndClosed : notEmptyAndClosed;
        this.normalizedWidgetValue = stripLeadingAndCondenseWhitespaces(inputValue);
        this.hintValue = autocompleWidget.getDefaultHintholderValue();

        fireEvent("autocomple:initialized", autocompleWidget.getTarget());
    }

    private SuggestionJso getHighlightedSuggestion() {
        return suggestionElementHighlighter.getHighlightedSuggestion();
    }

    private void setHintValue(SuggestionJso suggestionJso) {
        setHintValue(suggestionJso.getSuggestion());
    }

    private void setHintValue(String hintValue) {
        this.hintValue = hintValue.startsWith(normalizedWidgetValue) ? hintValue : EMPTY;

        Scheduler.get().scheduleDeferred(new Command() {
            @Override
            public void execute() {
                updateHintholderView();
            }
        });
    }

    private void updateHintholderView() {
        String hintValue = autocompleWidget.isInputHasOverflow() ? EMPTY : getAdjustedHintValue();
        autocompleWidget.setHintholderValue(hintValue);
    }

    private String getAdjustedHintValue() {
        String autocompleWidgetValue = autocompleWidget.getValue();
        String hintValueNoHtml = cleanupHtml(hintValue);
        String alignedNoHtmlHintValue = insertSpaces(autocompleWidgetValue, hintValueNoHtml);

        return adjustLetterCase(alignedNoHtmlHintValue, autocompleWidgetValue);
    }

    @Override
    public void onInput(Event event) {
        currentState.onInput();
    }

    @Override
    public void onFocus(Event event) {
        currentState.onFocus();
    }

    @Override
    public void onBlur(Event event) {
        currentState.onBlur();
    }

    @Override
    public void onKeydown(Event event) {
        switchKeydownEventHandler(this, event);
    }

    @Override
    public void onSuggestionClicked(ClickEvent event, Element parent, SuggestionJso suggestionJso) {
        currentState.onSuggestionClicked(suggestionJso);
    }

    @Override
    public void onSuggestionMouseOver(MouseOverEvent event, Element parent, SuggestionJso suggestionJso) {
        currentState.onSuggestionMouseOver(suggestionJso);
    }

    @Override
    public void onSuggestionMouseOut(MouseOutEvent event, Element parent, SuggestionJso suggestionJso) {
        currentState.onSuggestionMouseOut();
    }

    @Override
    public void onDropdownMouseDown(Event event) {
        event.preventDefault();
    }

    protected void onKeyboardUp(Event event) {
        currentState.onKeyboardUp(event);
    }

    protected void onKeyboardDown(Event event) {
        currentState.onKeyboardDown(event);
    }

    void onKeyboardEscape() {
        currentState.onKeyboardEscape();
    }

    void onKeyboardTab(Event event) {
        currentState.onKeyboardTab(event);
    }

    void onKeyboardRight(Event event) {
        currentState.onKeyboardRight(event);
    }

    void onKeyboardEnter(Event event) {
        currentState.onKeyboardEnter(event);
    }

}
