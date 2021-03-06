package org.jreact.core.impl;

import fj.Effect;
import fj.F;
import org.jreact.core.Reactive;
import org.jreact.core.Stream;

class VacuousStream<A>
        implements Stream<A> {

    static final VacuousStream INSTANCE = new VacuousStream();

    private VacuousStream() { }

    @Override
    public void loop(
            final Effect<? super A> effect) { }

    @Override
    public Reactive<A> limit(
            final int times) {

        return this;

    }

    @Override
    public Stream<A> limit(
            final Stream<?> dispose) {

        return this;

    }

    @Override
    @SuppressWarnings("unchecked")
    public <B> Stream<B> map(
            final F<? super A, ? extends B> function) {

        return (Stream<B>) this;

    }

    @Override
    public Stream<A> filter(
            final F<? super A, Boolean> predicate) {

        return this;

    }

    @SuppressWarnings("unchecked")
    static <A> VacuousStream<A> instance() {

        return VacuousStream.INSTANCE;

    }

}
