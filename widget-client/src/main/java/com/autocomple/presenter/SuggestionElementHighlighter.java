package com.autocomple.presenter;

import com.autocomple.data.SuggestionJso;
import com.autocomple.data.SuggestionListDataProvider;
import com.google.gwt.view.client.SelectionModel;

import java.util.List;

import static com.autocomple.common.Utils.EMPTY_SUGGESTION_JSO;

class SuggestionElementHighlighter {

    private final SelectionModel<? super SuggestionJso> selectionModel;
    private final SuggestionListDataProvider dataProvider;

    private SuggestionJso highlightedSuggestion = EMPTY_SUGGESTION_JSO;

    SuggestionElementHighlighter(SelectionModel<? super SuggestionJso> selectionModel,
                                 SuggestionListDataProvider dataProvider) {
        this.selectionModel = selectionModel;
        this.dataProvider = dataProvider;
    }

    public SuggestionJso getHighlightedSuggestion() {
        return highlightedSuggestion;
    }

    public boolean isHighlighted() {
        return highlightedSuggestion != EMPTY_SUGGESTION_JSO;
    }

    public void highlightPrevious() {
        changeSelection(prevIndex());
    }

    public void highlightNext() {
        changeSelection(nextIndex());
    }

    private int nextIndex() {
        List<SuggestionJso> suggestionJsos = dataProvider.getList();
        final int nextIndex = suggestionJsos.indexOf(highlightedSuggestion) + 1;
        final int maxIndex = suggestionJsos.size() - 1;

        return nextIndex > maxIndex ? -1 : nextIndex;
    }

    private int prevIndex() {
        List<SuggestionJso> suggestionJsos = dataProvider.getList();
        final int prevIndex = suggestionJsos.indexOf(highlightedSuggestion) - 1;
        final int maxIndex = suggestionJsos.size() - 1;

        return prevIndex < -1 ? maxIndex : prevIndex;
    }

    public void highlight(SuggestionJso suggestion) {
        selectionModel.setSelected(highlightedSuggestion = sanitize(suggestion), true);
    }

    public void resetHighlighting() {
        selectionModel.setSelected(highlightedSuggestion, false);
        highlightedSuggestion = EMPTY_SUGGESTION_JSO;
    }

    private SuggestionJso sanitize(SuggestionJso suggestion) {
        return suggestion == null ? EMPTY_SUGGESTION_JSO : suggestion;
    }

    private void changeSelection(int index) {
        try {
            highlight(dataProvider.getList().get(index));

        } catch (IndexOutOfBoundsException ignored) {
            resetHighlighting();
        }
    }

}
