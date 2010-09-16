package org.jreact.core;

import fj.F;
import fj.data.Option;

public interface Stream<A>
    extends Reactive<A>

{

    Reactive<A> limit(
        int times
    );

    @Override
    Disposable<? extends Stream<A>> limit(
        Stream<?> dispose
    );

    @Override
    <B> Disposable<? extends Stream<B>> map(
        F<? super A, ? extends B> function
    );

    Stream<A> filter(
        F<? super A, Boolean> predicate
    );

    <B> Stream<A> collect(
        F<? super A, Option<B>> function
    );

}
