package org.jreact.core.impl;

import org.jreact.core.Value;

abstract class AbstractValue<A>
        implements Value<A> {

    @Override
    public boolean equals(
            final Object object) {

        return object != null && object instanceof Value && equals((Value) object);

    }

    @Override
    public boolean equals(
            final Value value) {

        return value != null && get().equals(value.get());

    }

    @Override
    public int hashCode() {

        return get().hashCode();

    }

}
