package org.jreact.core;

import fj.Effect;
import fj.F;

public interface Reactive<A>

{

    void loop(
        Effect<? super Iterable<A>> effect
    );

    Disposable<? extends Reactive<A>> limit(
        Stream<?> dispose
    );

    <B> Disposable<? extends Reactive<B>> map(
        F<? super A, ? extends B> function
    );

}
