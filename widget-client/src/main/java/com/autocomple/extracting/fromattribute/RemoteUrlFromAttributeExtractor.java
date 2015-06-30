package com.autocomple.extracting.fromattribute;

import com.autocomple.config.AutocompleConfig;
import com.autocomple.extracting.ConfigExtractor;
import com.google.gwt.dom.client.InputElement;

import static com.autocomple.config.Tokens.REMOTE_URL_ATTR;

public class RemoteUrlFromAttributeExtractor implements ConfigExtractor<InputElement, AutocompleConfig> {

    @Override
    public AutocompleConfig extract(InputElement inputElement, AutocompleConfig config) {
        if (inputElement.hasAttribute(REMOTE_URL_ATTR)) {
            String remoteUrl = inputElement.getAttribute(REMOTE_URL_ATTR);
            config.setRemoteUrl(remoteUrl);
        }

        return config;
    }

}
