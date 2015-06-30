package com.autocomple.remote;

import com.autocomple.common.Logging;
import com.autocomple.data.SuggestionJso;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Iterator;
import java.util.Map;

public class AjaxTransport extends Transport {
    private Map<String, String> headers;

    public AjaxTransport(String url, String wildcard) {
        this(url, wildcard, null);
    }

    public AjaxTransport(String url, String wildcard, Map<String, String> headers) {
        super(url, wildcard);
        this.headers = headers;
    }

    @Override
    public void doGetSuggestions(String url, AsyncCallback<JsArray<SuggestionJso>> callback) {
        TransportRequestAdapter<JsArray<SuggestionJso>> callbackRequest =
                new TransportRequestAdapter<JsArray<SuggestionJso>>(callback);

        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));

        setHeaders(requestBuilder);

        try{
            requestBuilder.sendRequest(null, callbackRequest);
        } catch (RequestException e) {
            Logging.error("Request error " + url + ". Error message " + e.getMessage());
            callback.onFailure(e);
        }
    }

    private void setHeaders(RequestBuilder requestBuilder) {
        if(headers != null){
            Iterator headersIterator = headers.entrySet().iterator();

            while(headersIterator.hasNext()){
                Map.Entry<String, String> pair = (Map.Entry)headersIterator.next();
                requestBuilder.setHeader(pair.getKey(), pair.getValue());
            }
        }
    }
}
