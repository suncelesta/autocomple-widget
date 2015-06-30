package com.autocomple;

import com.google.gwt.core.client.EntryPoint;

public class App implements EntryPoint {

    public void onModuleLoad() {
        new AutocompleEmbedder().embed();
    }

}
