package com.autocomple.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.user.cellview.client.CellList;

public interface AutocompleBundle extends ClientBundle {

    public static final AutocompleBundle INSTANCE = GWT.create(AutocompleBundle.class);

    @Source("autocomple.css")
    Style css();

    @Source("autocomple.css")
    Style suggestionCellListCss();

    public interface Style extends CellList.Style {
        @ClassName("autocomple-input")
        String autocompleInput();

        @ClassName("autocomple-input-dark")
        String autocompleInputDark();

        @ClassName("autocomple-input-creme")
        String autocompleInputCreme();

        @ClassName("autocomple-slot")
        String autocompleSlot();

        @ClassName("autocomple-input-sm")
        String autocompleInputSm();

        @ClassName("autocomple-input-lg")
        String autocompleInputLg();

        @ClassName("autocomple-dropdown-dark")
        String autocompleDropdownDark();

        @ClassName("autocomple-dropdown-lg")
        String autocompleDropdownLg();

        @ClassName("autocomple-dropdown")
        String autocompleDropdown();

        @ClassName("autocomple-dropdown-creme")
        String autocompleDropdownCreme();

        @ClassName("autocomple-dropdown-sm")
        String autocompleDropdownSm();

        String suggestion();

        @ClassName("autocomple-hintholder-dark")
        String autocompleHintholderDark();

        @ClassName("autocomple-hintholder")
        String autocompleHintholder();

        @ClassName("autocomple-hintholder-creme")
        String autocompleHintholderCreme();

        @ClassName("autocomple-hintholder-lg")
        String autocompleHintholderLg();

        @ClassName("autocomple-hintholder-sm")
        String autocompleHintholderSm();

        String cellListEvenItem();

        String cellListOddItem();

        String cellListSelectedItem();

        String cellListKeyboardSelectedItem();

        String cellListWidget();
    }

}
