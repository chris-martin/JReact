package org.jreact.core.impl;

import fj.F;
import org.jreact.core.Stream;
import org.jreact.core.Value;

public class MappedSignal<A, B>
        extends AbstractSignal<B>
        implements ValueSink<A> {

    private final AbstractSignal<A> a;
    private final F<A, B> function;


    MappedSignal(
            final AbstractSignal<A> a,
            final F<A, B> function) {

        this.a = a;
        this.function = function;

    }

    @Override
    public B get() {

        return null;

    }

    @Override
    public void put(
            final Value<A> a) {



    }

    @Override
    public Stream<? extends B> changes() {

        return null;

    }

    @Override
    boolean disposed() {

        return a.disposed();

    }

}
