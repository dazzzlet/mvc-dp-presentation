package com.netcompany.demo.core;

public abstract class AbstractView<TModel extends Model> implements View {
    private TModel model;

    public TModel getModel() {
        return model;
    }

    public abstract EventArg updateModel(TModel model);
}
