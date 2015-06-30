package com.autocomple;

import com.autocomple.resources.AutocompleBundle;
import com.autocomple.embedding.TargetElementValidator;
import com.autocomple.embedding.ValidationException;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;

class AutocompleTargetElementValidator implements TargetElementValidator {
    @Override
    public void validate(Element element) throws ValidationException {
        validateNotNull(element);

        validateIsInput(element);

        InputElement inputElement = element.cast();

        validateTypeIsText(inputElement);

        validateNotAlreadyWrapped(inputElement);
    }

    private void validateNotNull(Element element) throws ValidationException {
        if (element == null) {
            throw new ValidationException("Input element to wrap not found.");
        }
    }

    private void validateTypeIsText(InputElement inputElement) throws ValidationException {
        if (!inputElement.getType().equalsIgnoreCase("text")) {
            throw new ValidationException("Autocomple can be enabled only for input elements with text type.");
        }
    }

    private void validateIsInput(Element element) throws ValidationException {
        if (!element.getTagName().equalsIgnoreCase(InputElement.TAG)) {
            throw new ValidationException("Autocomple can be enabled only for input elements with text type.");
        }
    }

    private void validateNotAlreadyWrapped(InputElement inputElement) throws ValidationException {
        String autocompleSlotClassName = AutocompleBundle.INSTANCE.css().autocompleSlot();
        Element element = inputElement;

        while (element.hasParentElement()) {
            Element parentElement = element.getParentElement();

            if (element.hasClassName(autocompleSlotClassName)) {
                throw new ValidationException("Input element already wrapped.");
            }

            element = parentElement;
        }
    }
}
