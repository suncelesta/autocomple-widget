package com.autocomple.embedding;

public interface ConfigValidator<Config> {

    void validate(Config config) throws ValidationException;

}
