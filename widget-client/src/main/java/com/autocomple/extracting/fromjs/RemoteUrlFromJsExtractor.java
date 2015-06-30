package com.autocomple.extracting.fromjs;

import com.autocomple.common.Jso;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;

public class RemoteUrlFromJsExtractor implements ConfigExtractor<Jso, AutocompleConfig> {

    @Override
    public AutocompleConfig extract(Jso jso, AutocompleConfig config) {
        Jso source = jso.getJso(Tokens.SOURCE);

        if (source != null) {
            Jso remote = source.getJso(Tokens.REMOTE);

            if (remote != null) {
                String url = remote.getString(Tokens.URL);

                if (url != null) {
                    config.setRemoteUrl(url);
                }
            }
        }

        return config;
    }

}
