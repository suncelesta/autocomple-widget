package com.autocomple.extracting.fromattribute;

import com.autocomple.common.Logging;
import com.autocomple.common.Jso;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.InputElement;

import java.util.Map;

import static com.autocomple.common.Utils.jsoToMap;

public class RemoteHeadersFromAttributeExtractor implements ConfigExtractor<InputElement, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(InputElement inputElement, AutocompleConfig config) {
        if (inputElement.hasAttribute(Tokens.REMOTE_HEADERS_ATTR)) {
            try {
                config.setRemoteHeaders(extractHeaders(inputElement));

            } catch (Exception ignored) {
                Logging.warning("Failed to parse json. Attribute '" + Tokens.REMOTE_HEADERS_ATTR + "' value '" +
                        attr(inputElement) + "' is not valid.");
            }
        }

        return config;
    }

    private Map<String, String> extractHeaders(InputElement inputElement) {
        String headersJsonString = attr(inputElement);

        Jso headers = JsonUtils.safeEval(headersJsonString);

        return jsoToMap(headers);
    }

    private String attr(InputElement inputElement) {
        return inputElement.getAttribute(Tokens.REMOTE_HEADERS_ATTR);
    }
}
