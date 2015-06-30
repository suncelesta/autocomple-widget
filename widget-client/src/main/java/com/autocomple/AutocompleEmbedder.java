package com.autocomple;

import com.autocomple.resources.AutocompleBundle;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.embedding.*;
import com.autocomple.mvp.Presenter;
import com.autocomple.presenter.AutocomplePresenter;
import com.autocomple.widget.AutocompleWidget;

import java.util.List;

public class AutocompleEmbedder extends Embedder<AutocompleConfig, AutocomplePresenter, AutocompleWidget> {

    private static ForEmbeddingsExtractor forEmbeddingsExtractor = new ForEmbeddingsExtractor();
    private static AutocompleTargetElementValidator autocompleTargetElementValidator = new AutocompleTargetElementValidator();
    private static AutocompleConfigValidator autocompleConfigValidator = new AutocompleConfigValidator();

    @Override
    protected void injectCss() {
        AutocompleBundle.INSTANCE.css().ensureInjected();
    }

    @Override
    protected List<ForEmbedding<AutocompleConfig>> getForEmbeddings() {
        return forEmbeddingsExtractor.extract();
    }

    @Override
    protected TargetElementValidator getTargetElementValidator() {
        return autocompleTargetElementValidator;
    }

    @Override
    protected ConfigValidator<AutocompleConfig> getConfigValidator() {
        return autocompleConfigValidator;
    }

    @Override
    protected AutocompleWidget createWidget() {
        return new AutocompleWidget();
    }

    @Override
    protected AutocomplePresenter createPresenter(AutocompleWidget widget) {
        return new AutocomplePresenter((AutocompleWidget) widget);// todo: autocomple widget specific methods to view
    }

}
