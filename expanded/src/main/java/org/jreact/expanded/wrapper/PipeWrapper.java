package org.jreact.expanded.wrapper;

import fj.Effect;
import fj.F;
import org.jreact.core.Pipe;
import org.jreact.core.Reactive;
import org.jreact.core.Stream;

public class PipeWrapper<A>
        implements Pipe<A> {

    final Pipe<A> pipe;

    public PipeWrapper(
            final Pipe<A> pipe) {

        this.pipe = pipe;

    }

    @Override
    public void loop(
            final Effect<? super A> effect) {

        pipe.loop(
            effect
        );

    }

    @Override
    public void put(
            final A value) {

        pipe.put(
            value
        );

    }

    @Override
    public Effect<A> putEffect() {

        return pipe.putEffect();

    }

    @Override
    public <B> Effect<B> putEffect(
            final A value) {

        return pipe.putEffect(
            value
        );

    }

    @Override
    public Reactive<A> limit(
            final int times) {

        return pipe.limit(
            times
        );

    }

    @Override
    public Stream<A> limit(
            final Stream<?> dispose) {

        return pipe.limit(
            dispose
        );

    }

    @Override
    public <B> Stream<B> map(
            final F<? super A, ? extends B> function) {

        return pipe.map(
            function
        );

    }

    @Override
    public Stream<A> filter(
            final F<? super A, Boolean> predicate) {

        return pipe.filter(
            predicate
        );

    }

}
