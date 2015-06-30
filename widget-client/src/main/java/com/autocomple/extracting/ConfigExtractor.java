package com.autocomple.extracting;

public interface ConfigExtractor<From, Config> {

    Config extract(From from, Config config);

}
