package com.autocomple.extracting;

import com.autocomple.config.AutocompleConfig;

import java.util.HashSet;
import java.util.Set;

public class ConfigMultiExtractor<From> implements ConfigExtractor<From, AutocompleConfig> {

    private Set<ConfigExtractor<From, AutocompleConfig>> sanitizers = new HashSet<>();

    @Override
    public AutocompleConfig extract(From from, AutocompleConfig config) {
        for (ConfigExtractor<From, AutocompleConfig> sanitizer : sanitizers) {
            config = sanitizer.extract(from, config);
        }

        return config;
    }

    public void addExtractor(ConfigExtractor<From, AutocompleConfig> sanitizer) {
        sanitizers.add(sanitizer);
    }
}
