package org.jreact.core.impl;

import fj.Effect;
import fj.F;
import org.jreact.core.Reactive;
import org.jreact.core.Signal;
import org.jreact.core.Stream;
import org.jreact.core.Value;

class DisposableConstant<A>
        extends ValueConstant<A> {

    private boolean disposed;

    DisposableConstant(
            final Value<A> value) {

        super(value);

    }

    void dispose() {

        value = null;
        a = null;

    }

    @Override
    public void loop(
            final Effect<? super A> effect) {

        if (!disposed) {
            super.loop(effect);
        }

    }

}
