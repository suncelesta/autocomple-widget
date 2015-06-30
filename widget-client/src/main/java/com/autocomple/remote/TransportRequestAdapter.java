package com.autocomple.remote;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.StatusCodeException;

public class TransportRequestAdapter<T> implements RequestCallback{
    private AsyncCallback<T> asyncCallback;

    public TransportRequestAdapter(AsyncCallback<T> asyncCallback) {
        this.asyncCallback = asyncCallback;
    }

    @Override
    public void onResponseReceived(Request request, Response response) {
        T result = null;
        Throwable caught = null;

        try {
            String encodedResponse = response.getText();
            int statusCode = response.getStatusCode();

            if (statusCode != Response.SC_OK) {
                caught = new StatusCodeException(statusCode, response.getStatusText(), encodedResponse);
            } else if (encodedResponse == null) {
                caught = new InvocationException("No response payload from received from server");
            } else {
                result = (T) JsonUtils.safeEval(response.getText());
            }
        } catch (Throwable e) {
            caught = e;
        }

        if (caught == null) {
            asyncCallback.onSuccess(result);
        } else {
            asyncCallback.onFailure(caught);
        }

    }

    @Override
    public void onError(Request request, Throwable exception) {
        asyncCallback.onFailure(exception);
    }
}
