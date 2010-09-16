package org.jreact.core;

import fj.Effect;
import fj.F;
import fj.Unit;

import static org.jreact.core.Disposable.disposable;

public final class ImmediateVaryingReactive<A>
    implements Reactive<A>, Varying<A>

{

    private final ImmediateReactive<A> immediate;

    private final Stream<A> stream;

    private final Effect<Unit> dispose = new Effect<Unit>() {
        @Override public void e(final Unit unit) {

        }
    };

    private ImmediateVaryingReactive(
        final ImmediateReactive<A> immediate,
        final Stream<A> stream
    )
    {
        this.immediate = immediate;
        this.stream = stream;
    }

    @Override
    public void loop(
        final Effect<? super Iterable<A>> effect
    ) {
        immediate.loop(effect);
        stream.loop(effect);
    }

    @Override
    public Disposable<? extends Reactive<A>> limit(
        final Stream<?> dispose
    ) {
        final ImmediateVaryingReactive<A> limited = immediateVaryingReactive(
            immediate.limit(dispose).get(),
            stream.limit(dispose).get()
        );
        return disposable(
            limited,
            limited.dispose
        );
    }

    @Override
    public <B> Disposable<? extends Reactive<B>> map(
        final F<? super A, ? extends B> function
    ) {
        final ImmediateVaryingReactive<B> mapped = immediateVaryingReactive(
            immediate.map(function).get(),
            stream.map(function).get()
        );
        return disposable(
            mapped,
            mapped.dispose
        );
    }

    @Override
    public Stream<? extends A> changes() {
        return stream;
    }

    public static <A> ImmediateVaryingReactive<A> immediateVaryingReactive(
        final ImmediateReactive<A> immediate,
        final Stream<A> stream
    ) {
        return new ImmediateVaryingReactive<A>(immediate, stream);
    }

}
