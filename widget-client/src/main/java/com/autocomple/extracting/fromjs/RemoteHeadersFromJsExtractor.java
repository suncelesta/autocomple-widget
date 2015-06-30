package com.autocomple.extracting.fromjs;

import com.autocomple.common.Jso;
import com.autocomple.common.Utils;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;

import java.util.Map;

public class RemoteHeadersFromJsExtractor implements ConfigExtractor<Jso, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(Jso jso, AutocompleConfig config) {
        Jso source = jso.getJso(Tokens.SOURCE);

        if (source != null) {
            Jso remote = source.getJso(Tokens.REMOTE);

            if (remote != null) {
                Jso headersJso = remote.getJso(Tokens.HEADERS);

                if (headersJso != null) {
                    Map<String, String> headers = Utils.jsoToMap(headersJso);
                    config.setRemoteHeaders(headers);
                }
            }
        }

        return config;
    }
}
