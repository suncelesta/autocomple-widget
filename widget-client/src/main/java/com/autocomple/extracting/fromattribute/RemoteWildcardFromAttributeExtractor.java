package com.autocomple.extracting.fromattribute;

import com.autocomple.config.AutocompleConfig;
import com.autocomple.extracting.ConfigExtractor;
import com.google.gwt.dom.client.InputElement;

import static com.autocomple.config.Tokens.REMOTE_WILDCARD_ATTR;

public class RemoteWildcardFromAttributeExtractor implements ConfigExtractor<InputElement, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(InputElement inputElement, AutocompleConfig config) {
        if (inputElement.hasAttribute(REMOTE_WILDCARD_ATTR)) {
            String wildcard = inputElement.getAttribute(REMOTE_WILDCARD_ATTR);
            config.setWildcard(wildcard);
        }

        return config;
    }


}
