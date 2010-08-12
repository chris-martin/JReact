package org.jreact.core.impl;

import org.jreact.core.Variable;

class VariableImpl<A>
        extends SignalImpl<A>
        implements Variable<A> {

    A value;

    PipeImpl<A> changes;

    public VariableImpl(
            final A value) {

        assert value != null;

        this.value = value;

    }

    @Override
    public void put(
            final A value) {

        assert value != null;

        if (!this.value.equals(value)) {
            this.value = value;
            changes().put(value);
        }

    }

    @Override
    public A get() {

        return value;

    }

    @Override
    public PipeImpl<A> changes() {

        if (changes == null) {
            changes = new PipeImpl<A>();
        }

        return changes;

    }

    @Override
    public boolean disposed() {

        return false;

    }

}
