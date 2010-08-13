package org.jreact.core.impl;

class SimpleConstant<A>
        extends ConstantImpl<A> {

    A value;

    SimpleConstant(
            final A value) {

        this.value = value;

    }

    @Override
    public A get() {

        return value;

    }

}
