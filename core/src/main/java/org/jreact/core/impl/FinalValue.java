package org.jreact.core.impl;

class FinalValue<A>
        extends AbstractValue<A> {

    final A value;

    FinalValue(
            final A value) {

        this.value = value;

    }

    @Override
    public A get() {

        return value;

    }

}
