package org.jreact.core;

import fj.Effect;
import fj.F;

public interface Reactive<A> {

    void loop(
        Effect<? super A> effect
    );

    Reactive<A> limit(
        Stream<?> dispose
    );

    <B> Reactive<B> map(
        F<? super A, ? extends B> function
    );

}
