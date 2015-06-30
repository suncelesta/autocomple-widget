package com.autocomple.data;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SuggestionsAsyncProvider {

    public void getSuggestions(String nGram, AsyncCallback<JsArray<SuggestionJso>> callback);

}
