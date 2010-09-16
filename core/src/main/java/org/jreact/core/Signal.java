package org.jreact.core;

import fj.F;

public interface Signal<A>
    extends Reactive<A>, Iterable<A>, Varying<A>

{

    @Override
    <B> Signal<B> map(
        F<? super A, ? extends B> function
    );

    <B, C> Signal<C> compose(
        Signal<B> signal,
        F<? super B, ? extends C> function
    );

}
