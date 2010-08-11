package org.jreact.core.impl;

import fj.Effect;
import fj.F;
import org.jreact.core.Signal;
import org.jreact.core.Value;

public class DisposableConstant<A>
        extends ConstantImpl<A> {

    final Value<A> value;
    boolean disposed;

    DisposableConstant(
            final Value<A> value) {

        this.value = value;

    }

    DisposableConstant(
            final Value<A> value,
            final boolean disposed) {

        this.value = value;
        this.disposed = disposed;

    }

    @Override
    public A get() {

        return value.get();

    }

    void dispose() {

        disposed = true;

    }

    @Override
    public void loop(
            final Effect<? super A> effect) {

        if (value != null) {
            super.loop(effect);
        }

    }

    @Override
    public <B> Signal<B> map(
            final F<A, B> function) {

        if (!disposed) {
            return super.map(function);
        } else {
            return new DisposableConstant<B>
        }

    }

}
