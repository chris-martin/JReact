package org.jreact.core.impl;

import fj.F;
import org.jreact.core.Value;

class MappedPipe<A, B>
        extends StreamImpl<B>
        implements ValueSink<A> {

    final F<? super A, ? extends B> function;

    public MappedPipe(
            final F<? super A, ? extends B> function) {

        this.function = function;

    }

    @Override
    public void put(
            final Value<A> value) {

        propagate(new AbstractValue<B>() {
            @Override
            public B get() {
                return function.f(value.get());
            }
        });

    }

}
