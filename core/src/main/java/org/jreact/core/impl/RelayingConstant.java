package org.jreact.core.impl;

import org.jreact.core.Value;

class RelayingConstant<A>
        extends ConstantImpl<A> {

    Value<A> value;
    A a;

    RelayingConstant(
            final Value<A> value) {

        this.value = value;

    }

    @Override
    public A get() {

        if (a == null) {
            a = value.get();
            value = null;
        }

        return a;

    }

}
