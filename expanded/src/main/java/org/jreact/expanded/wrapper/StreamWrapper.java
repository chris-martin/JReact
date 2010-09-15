package org.jreact.expanded.wrapper;

import fj.Effect;
import fj.F;
import org.jreact.core.Reactive;
import org.jreact.core.Stream;

public class StreamWrapper<A>
        implements Stream<A> {

    private final Stream<A> stream;

    public StreamWrapper(
            final Stream<A> stream) {

        this.stream = stream;

    }

    @Override
    public Reactive<A> limit(
            final int times) {

        return stream.limit(times);

    }

    @Override
    public Stream<A> limit(
            final Stream<?> dispose) {

        return stream.limit(dispose);

    }

    @Override
    public <B> Stream<B> map(
            final F<? super A, ? extends B> function) {

        return stream.map(function);

    }

    @Override
    public Stream<A> filter(F<? super A, Boolean> predicate) {

        return stream.filter(predicate);

    }

    @Override
    public void loop(
            final Effect<? super A> effect) {

        stream.loop(effect);

    }

}
