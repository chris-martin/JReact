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

    @Override
    void dispose() {

        throw new UnsupportedOperationException();

    }

    @Override
    public boolean disposed() {

        return false;

    }

}
