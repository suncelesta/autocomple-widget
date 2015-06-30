package com.autocomple.remote;

import com.autocomple.data.SuggestionJso;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class JsonpTransport extends Transport {
    private JsonpRequestBuilder JSONP = new JsonpRequestBuilder();

    public JsonpTransport(String url, String wildcard) {
        super(url, wildcard);
    }

    @Override
    public void doGetSuggestions(String url, AsyncCallback<JsArray<SuggestionJso>> callback) {
        JSONP.requestObject(url, callback);
    }
}
