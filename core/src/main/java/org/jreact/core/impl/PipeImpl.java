package org.jreact.core.impl;

import fj.Effect;
import org.jreact.core.Pipe;
import org.jreact.core.Value;

class PipeImpl<A>
        extends StreamImpl<A>
        implements Pipe<A>, ValueSink<A> {

    @Override
    public void put(
            final A value) {

        propagate(value);

    }

    @Override
    public void put(
            final Value<A> value) {

        propagate(value);

    }

    @Override
    public Effect<A> put() {

        return new Effect<A>() {

            @Override
            public void e(
                    final A a) {

                PipeImpl.this.put(a);

            }

        };

    }

}
