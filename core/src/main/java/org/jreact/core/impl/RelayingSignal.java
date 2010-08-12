package org.jreact.core.impl;

import org.jreact.core.Value;

class RelayingSignal<A>
        extends SignalImpl<A> {

    final Value<? extends A> value;
    final StreamImpl<? extends A> changes;
    boolean disposed;

    public RelayingSignal(
            final Value<? extends A> value,
            final StreamImpl<? extends A> changes) {

        this.value = value;
        this.changes = changes;

    }

    @Override
    public A get() {

        return value.get();

    }

    @Override
    public StreamImpl<? extends A> changes() {

        return changes;

    }

    @Override
    public boolean disposed() {

        return disposed;

    }

    void dispose() {

        disposed = true;

    }

}
