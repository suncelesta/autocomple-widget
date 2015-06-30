package com.autocomple.remote;


import com.autocomple.data.SuggestionJso;
import com.autocomple.data.SuggestionsAsyncProvider;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.LinkedList;

import static com.autocomple.common.StringUtils.isBlank;

public abstract class Transport implements SuggestionsAsyncProvider {
    protected class TransportCallbackDecorator<T> implements AsyncCallback<T> {
        private AsyncCallback<T> asyncCallback;
        private boolean canceled = false;

        protected TransportCallbackDecorator(AsyncCallback<T> callback) {
            this.asyncCallback = callback;
        }

        @Override
        public void onFailure(Throwable caught) {
            asyncCallback.onFailure(caught);
        }

        @Override
        public void onSuccess(T result) {
            if (!canceled) {
                cancelEarlyPendingRequests();

                asyncCallback.onSuccess(result);
            }
        }

        private void cancelEarlyPendingRequests() {
            TransportCallbackDecorator<JsArray<SuggestionJso>> callback;

            do {
                callback = pendingCallbacks.removeFirst();
                if (callback != this) {
                    callback.cancel();
                }
            } while (callback != this);
        }

        public void cancel() {
            canceled = true;
        }
    }

    protected String url;
    protected String wildcard;
    private LinkedList<TransportCallbackDecorator<JsArray<SuggestionJso>>> pendingCallbacks = new LinkedList<>();

    protected Transport(String url, String wildcard) {
        this.url = url;
        this.wildcard = wildcard;
    }

    protected String buildUrl(String nGram) {
        return url.replace(wildcard, nGram);
    }

    public abstract void doGetSuggestions(String url, AsyncCallback<JsArray<SuggestionJso>> callback);

    @Override
    public void getSuggestions(String nGram, AsyncCallback<JsArray<SuggestionJso>> callback) {
        if (isBlank(nGram)) return;
        TransportCallbackDecorator<JsArray<SuggestionJso>> transportCallbackDecorator =
                new TransportCallbackDecorator<>(callback);

        doGetSuggestions(buildUrl(nGram), transportCallbackDecorator);
        pendingCallbacks.addLast(transportCallbackDecorator);
    }

    public void cancelPendingRequests() {
        for (TransportCallbackDecorator<JsArray<SuggestionJso>> pendingCallback : pendingCallbacks) {
            pendingCallback.cancel();
        }
    }
}
