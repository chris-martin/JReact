package org.jreact.core.impl;

import org.jreact.core.Value;

class LazyConstant<A>
        extends ConstantImpl<A> {

    A value;
    Value<A> container;

    LazyConstant(
            final Value<A> container) {

        this.container = container;

    }

    @Override
    public A get() {

        if (value == null) {
            value = container.get();
            container = null;
        }

        return value;

    }

    

}
