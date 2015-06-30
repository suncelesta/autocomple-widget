package com.autocomple.presenter;


import com.autocomple.data.SuggestionJso;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.user.client.Event;

public interface DropdownUiHandlers extends UiHandlers {

    void onSuggestionClicked(ClickEvent event, Element parent, SuggestionJso suggestionJso);

    void onSuggestionMouseOver(MouseOverEvent event, Element parent, SuggestionJso suggestionJso);

    void onSuggestionMouseOut(MouseOutEvent event, Element parent, SuggestionJso suggestionJso);

    void onDropdownMouseDown(Event event);
}
