package org.jreact.core.impl;

import org.jreact.core.Value;

class RelayingSignal<A>
        extends SignalImpl<A> {

    final Value<? extends A> value;
    final StreamImpl<A> changes;

    public RelayingSignal(
            final Value<? extends A> value,
            final StreamImpl<A> changes) {

        this.value = value;
        this.changes = changes;

    }

    @Override
    public A get() {

        return value.get();

    }

    @Override
    public StreamImpl<A> changes() {

        return changes;

    }

}
