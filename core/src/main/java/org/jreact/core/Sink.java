package org.jreact.core;

public interface Sink<A>

{

    void sink(
        Iterable<? extends A> values
    );

}
