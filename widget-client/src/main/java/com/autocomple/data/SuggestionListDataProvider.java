package com.autocomple.data;


import com.autocomple.remote.Transport;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;

import java.util.List;

import static com.autocomple.common.Utils.EMPTY_SUGGESTION_JSO;

// todo: make remote request first, than get from cache.
public class SuggestionListDataProvider extends ListDataProvider<SuggestionJso> implements SuggestionsAsyncProvider {
    private class UpdateListCallbackDecorator implements AsyncCallback<JsArray<SuggestionJso>> {
        private AsyncCallback<JsArray<SuggestionJso>> callback;

        private UpdateListCallbackDecorator(AsyncCallback<JsArray<SuggestionJso>> callback) {
            this.callback = callback;
        }

        @Override
        public void onFailure(Throwable caught) {
            callback.onFailure(caught);
        }

        @Override
        public void onSuccess(JsArray<SuggestionJso> result) {
            updateSuggestionListDataProvider(result);
            callback.onSuccess(result);
        }
    }

    private Transport transport;
    private int suggestionsLimit;

    public SuggestionListDataProvider(Transport transport, int suggestionsLimit) {
        this.transport = transport;
        this.suggestionsLimit = suggestionsLimit;
    }


    @Override
    public void getSuggestions(String nGram, AsyncCallback<JsArray<SuggestionJso>> callback) {
        UpdateListCallbackDecorator callbackDecorator = new UpdateListCallbackDecorator(callback);
        transport.getSuggestions(nGram, callbackDecorator);
    }

    public void clear(){
        this.getList().clear();
    }

    public SuggestionJso getTopSuggestion() {
        List<SuggestionJso> list = this.getList();

        return list.isEmpty() ? EMPTY_SUGGESTION_JSO : list.get(0);
    }

    private void updateSuggestionListDataProvider(JsArray<SuggestionJso> suggestions) {
        List<SuggestionJso> list = this.getList();
        list.clear();
        for (int i = 0; i < suggestions.length() && i < suggestionsLimit; i++) {
            list.add(suggestions.get(i));
        }
    }
}
