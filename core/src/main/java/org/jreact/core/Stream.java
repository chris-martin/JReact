package org.jreact.core;

import fj.F;

public interface Stream<A>
        extends Reactive<A> {

    Reactive<A> limit(
        int times
    );

    @Override
    Stream<A> limit(
        Stream<?> dispose
    );

    @Override
    <B> Stream<B> map(
        F<? super A, ? extends B> function
    );

    Stream<A> filter(
        F<? super A, Boolean> predicate
    );

}
