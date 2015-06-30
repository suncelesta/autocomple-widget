package com.autocomple;

import com.autocomple.config.AutocompleConfig;
import com.autocomple.embedding.ConfigValidator;
import com.autocomple.embedding.ValidationException;

class AutocompleConfigValidator implements ConfigValidator<AutocompleConfig> {
    @Override
    public void validate(AutocompleConfig autocompleConfig) throws ValidationException {
        // todo: validate wildcard
    }
}
