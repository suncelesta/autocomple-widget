package com.autocomple.embedding;

import com.autocomple.common.Logging;
import com.autocomple.mvp.Presenter;
import com.google.gwt.dom.client.Element;

import java.util.List;

public abstract class Embedder<Config, P extends Presenter, W extends EmbeddableWidget<Config>> {

    public void embed() {
        injectCss();

        for (ForEmbedding<Config> forEmbedding : getForEmbeddings()) {
            try {
                embed(forEmbedding);
            } catch (ValidationException e) {
                Logging.error(e.getMessage());
            }
        }
    }

    private Presenter embed(ForEmbedding<Config> forEmbedding) throws ValidationException {
        Element target = forEmbedding.getTarget();
        getTargetElementValidator().validate(target);

        Config config = forEmbedding.getConfig();
        getConfigValidator().validate(config);

        W widget = createWidget();
        widget.embed(target, config);

        return createPresenter(widget);
    }

    protected abstract void injectCss();

    protected abstract List<ForEmbedding<Config>> getForEmbeddings();

    protected abstract TargetElementValidator getTargetElementValidator();

    protected abstract ConfigValidator<Config> getConfigValidator();

    protected abstract P createPresenter(W widget);

    protected abstract W createWidget();

}
