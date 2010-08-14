package org.jreact.core.impl;

import fj.Effect;
import fj.F;
import org.jreact.core.Reactive;
import org.jreact.core.Stream;
import org.jreact.core.Value;

import java.util.ArrayList;
import java.util.List;

class StreamImpl<A>
        implements Stream<A>, Reactive<A> {

    List<Effect<? super A>> dependentEffects;
    List<ValueSink<A>> dependentValueSinks;
    boolean disposed;

    StreamImpl() {

        dependentEffects = new ArrayList<Effect<? super A>>();
        dependentValueSinks = new ArrayList<ValueSink<A>>();

    }

    @Override
    public void loop(
            final Effect<? super A> effect) {

        assert effect != null;

        if (!disposed()) {
            dependentEffects.add(effect);
        }

    }

    void propagate(
            final A value) {

        assert value != null;

        if (!disposed()) {
            propagateToPipes(new SimpleConstant<A>(value));
            propagateToEffects(value);
        }

    }

    void propagate(
            final Value<A> value) {

        assert value != null;

        if (!disposed()) {
            final A v = value.get();
            assert v != null;
            propagateToPipes(value);
            propagateToEffects(v);
        }

    }

    private void propagateToEffects(
            final A value) {

        for (final Effect<? super A> effect
                : new ArrayList<Effect<? super A>>(dependentEffects)) {
            effect.e(value);
        }

    }

    private void propagateToPipes(
            final Value<A> value) {

        for (final ValueSink<A> sink
                : new ArrayList<ValueSink<A>>(dependentValueSinks)) {
            sink.put(value);
        }

    }

    void removeValueSink(
            final ValueSink<A> sink) {

        if (dependentValueSinks != null) {
            final boolean removed = dependentValueSinks.remove(sink);
            assert removed;
        }

    }

    private <B extends ValueSink<A>> B addValueSink(
            final B sink) {

        dependentValueSinks.add(sink);

        return sink;

    }


    @Override
    public Reactive<A> limit(
            final int times) {

        assert times >= 0;

        if (times == 0) {

            return VacuousStream.instance();

        } else {

            return addValueSink(
                new RelayingPipeLimitedTimes<A>(
                    this,
                    times
                )
            );

        }

    }

    @Override
    public PipeImpl<A> limit(
            final Stream<?> dispose) {

        assert dispose != null;

        final PipeImpl<A> limitedSource = new RelayingPipe<A>(this);

        dispose.limit(1).loop(new Effect<Object>() {
            @Override
            public void e(final Object o) {
                limitedSource.dispose();
            }
        });

        dependentValueSinks.add(limitedSource);

        return limitedSource;

    }

    void dispose() {

        assert !disposed;

        dependentEffects = null;
        dependentValueSinks = null;
        disposed = true;

    }

    public boolean disposed() {

        return disposed;

    }

    @Override
    public <B> Stream<B> map(
            final F<? super A, B> function) {

        if (disposed()) {

            return VacuousStream.instance();

        } else {

            return addValueSink(
                new MappedPipe<A, B>(
                    function
                )
            );

        }

    }

}
