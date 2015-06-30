package com.autocomple.embedding;

import com.google.gwt.dom.client.Element;

public interface TargetElementValidator {

    void validate(Element element) throws ValidationException;

}
