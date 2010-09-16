package org.jreact.core;

import fj.Effect;
import fj.F;
import fj.Unit;
import fj.data.IterableW;

import static fj.Function.constant;
import static fj.Function.vary;
import static fj.Unit.unit;
import static org.jreact.core.DisposableList.disposableList;

public final class ImmediateReactive<A>
    implements Reactive<A>

{

    private IterableW<A> immediate;

    private DisposableList dependents = disposableList();

    private final Effect<Unit> dispose = new Effect<Unit>() {
        @Override public void e(final Unit unit) {
            immediate = null;
            dependents.dispose();
            dependents = null;
        }
    };

    public ImmediateReactive(
        final Iterable<A> immediate
    ) {
        if (immediate == null) throw new NullPointerException();
        this.immediate = IterableW.wrap(immediate);
    }

    @Override
    public void loop(
        final Effect<? super Iterable<A>> effect
    ) {
        if (immediate != null) {
            effect.e(immediate);
        }
    }

    @Override
    public Disposable<? extends ImmediateReactive<A>> limit(
        final Stream<?> dispose
    ) {
        final ImmediateReactive<A> limited = immediateReactive(immediate);
        dispose.limit(1).loop(limited.dispose.comap(constant(unit())));
        return dependents.add(limited, limited.dispose);
    }

    @Override
    public <B> Disposable<? extends ImmediateReactive<B>> map(
        final F<? super A, ? extends B> function
    ) {
        final ImmediateReactive<B> mapped =
            immediateReactive(immediate.map(vary(function)));
        return dependents.add(mapped, mapped.dispose);
    }

    public static <A> ImmediateReactive<A> immediateReactive(
        final Iterable<A> immediate
    ) {
        return new ImmediateReactive<A>(immediate);
    }

}
