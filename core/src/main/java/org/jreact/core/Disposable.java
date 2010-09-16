package org.jreact.core;

import fj.Effect;
import fj.Unit;

import static fj.Unit.unit;

public final class Disposable<A>

{

    private final A a;

    private final Effect<Unit> dispose;

    public Disposable(
        final A a,
        final Effect<Unit> dispose
    )
    {
        this.a = a;
        this.dispose = dispose;
    }

    public void dispose() {

        dispose.e(
            unit()
        );

    }

    public A get() {

        return a;

    }

    public static <A> Disposable<A> disposable(
        final A a,
        final Effect<Unit> dispose
    )
    {
        return new Disposable<A>(
            a,
            dispose
        );
    }

}
