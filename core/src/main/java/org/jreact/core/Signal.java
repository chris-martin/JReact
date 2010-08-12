package org.jreact.core;

import fj.F;

public interface Signal<A>
        extends Reactive<A>, Value<A>, Varying<A> {

    @Override
    <B> Signal<B> map(
        F<? super A, B> function
    );

}
