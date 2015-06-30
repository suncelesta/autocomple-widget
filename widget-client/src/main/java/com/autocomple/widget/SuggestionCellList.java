package com.autocomple.widget;

import com.autocomple.resources.AutocompleBundle;
import com.autocomple.data.SuggestionJso;
import com.autocomple.presenter.DropdownUiHandlers;
import com.autocomple.presenter.HasUiHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Event;
import com.google.gwt.view.client.SingleSelectionModel;


public class SuggestionCellList extends CellList<SuggestionJso>
        implements HasUiHandlers<DropdownUiHandlers> {


    public SuggestionCellList() {
        super(new SuggestionCell(), new Resources() {
            @Override
            public ImageResource cellListSelectedBackground() {
                return null;
            }

            @Override
            public Style cellListStyle() {
                return AutocompleBundle.INSTANCE.suggestionCellListCss();
            }
        });

        setSelectionModel(new SingleSelectionModel<SuggestionJso>());
        setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
        setTabIndex(-1);
    }

    @Override
    protected void onBrowserEvent2(Event event) {
        DropdownUiHandlers uiHandlers = getUiHandlers();

        if (event.getType().equals("mousedown") && uiHandlers != null) {
            uiHandlers.onDropdownMouseDown(event);
        }

        super.onBrowserEvent2(event);
    }

    @SuppressWarnings("unchecked")
    @Override
    public DropdownUiHandlers getUiHandlers() {
        return ((HasUiHandlers<DropdownUiHandlers>) getCell()).getUiHandlers();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setUiHandlers(DropdownUiHandlers uiHandlers) {
        ((HasUiHandlers<DropdownUiHandlers>) getCell()).setUiHandlers(uiHandlers);
    }
}
