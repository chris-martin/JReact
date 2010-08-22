package org.jreact.expanded.effect;

import fj.Effect;
import org.jreact.core.Sink;

public class SetEffect<A>
        extends Effect<A> {

    final Sink<? super A> sink;

    public SetEffect(
            final Sink<? super A> sink) {

        this.sink = sink;

    }

    @Override
    public void e(final A a) {

        sink.put(a);

    }

}
