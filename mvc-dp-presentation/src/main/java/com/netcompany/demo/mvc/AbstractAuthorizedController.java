package com.netcompany.demo.mvc;

import com.netcompany.demo.core.AbstractController;
import com.netcompany.demo.core.ApplicationContext;
import com.netcompany.demo.core.Model;
import com.netcompany.demo.dto.Identity;

public abstract class AbstractAuthorizedController<TModel extends Model> extends AbstractController<TModel> {
    public AbstractAuthorizedController(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public Identity getIdentity() {
        return this.getApplicationContext().getIdentity();
    }
}
