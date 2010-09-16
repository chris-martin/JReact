package org.jreact.expanded.suspend;

import fj.Effect;

public class SuspendableSink<A>
        implements Sink<A>, Suspendable {

    private final Sink<A> sink;

    private boolean suspended;

    public SuspendableSink(
            final Sink<A> sink) {

        this.sink = sink;

    }

    @Override
    public void suspend(
            final boolean suspended) {

        this.suspended = suspended;

    }

    @Override
    public void put(
            final A value) {

        if (!suspended) {
            sink.put(value);
        }

    }

    @Override
    public Effect<A> putEffect() {

        return new Effect<A>() {

            @Override
            public void e(
                    final A a) {

                SuspendableSink.this.put(a);

            }

        };

    }

    @Override
    public <B> Effect<B> putEffect(
            final A value) {

        return new Effect<B>() {

            @Override
            public void e(
                    final B a) {

                SuspendableSink.this.put(value);

            }

        };

    }

    public static <A> SuspendableSink<A> suspendableSink(
            final Sink<A> sink) {

        return new SuspendableSink<A>(
            sink
        );

    }

}
