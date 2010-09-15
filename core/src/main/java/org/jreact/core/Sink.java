package org.jreact.core;

import fj.Effect;

public interface Sink<A> {

    void put(
        A value
    );

    /**
     * @return
     *  A first-class version of the {@link #put(Object)} method.
     */
    Effect<A> putEffect();

    <B> Effect<B> putEffect(
        A value
    );

}
