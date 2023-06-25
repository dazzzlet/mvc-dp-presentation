package com.netcompany.demo.core;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractAuthorizedCollectionMenu extends AbstractAuthorizedMenu {

    public AbstractAuthorizedCollectionMenu(ConsoleContext appCtx) {
        super(appCtx);
    }

    protected abstract List<MenuItem> getCollection();

    protected abstract List<MenuItem> getAdditionalMenuItem();

    protected abstract void launchCollectionItem(MenuItem menuItem);

    protected abstract void launchAdditionalMenuItem(MenuItem menuItem);

    @Override
    public List<MenuItem> getMenuItems() {
        return Stream.of(this.getCollection(), this.getAdditionalMenuItem())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    protected void launchMenuItem(int index) {
        MenuItem menuItem;
        int collectionSize = this.getCollection().size();
        if (index >= collectionSize) {
            menuItem = this.getAdditionalMenuItem().get(index - collectionSize);
            this.launchAdditionalMenuItem(menuItem);
        } else {
            menuItem = this.getCollection().get(index);
            this.launchCollectionItem(menuItem);
        }
    }
}
