package com.autocomple.embedding;

import com.autocomple.mvp.View;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;

public abstract class EmbeddableWidget<Config> extends Composite implements View {

    protected abstract void embed(Element target, Config config);

}
