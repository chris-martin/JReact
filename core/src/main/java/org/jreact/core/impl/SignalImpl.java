package org.jreact.core.impl;

import fj.Effect;
import fj.F;
import org.jreact.core.*;

abstract class SignalImpl<A>
        implements Signal<A>, Varying<A> {

    @Override
    public abstract StreamImpl<A> changes();

    @Override
    public void loop(
            final Effect<? super A> effect) {

        assert effect != null;

        if (!disposed()) {
            effect.e(get());
            changes().loop(effect);
        }

    }

    @Override
    public Reactive<A> limit(
            final Stream<?> dispose) {

        assert dispose != null;

        if (disposed()) {
            return VacuousStream.instance();
        } else {
            final RelayingSignal<A> limited = new RelayingSignal<A>(
                this,
                changes().limit(dispose)
            );
            dispose.limit(1).loop(new Effect<Object>() {
                @Override
                public void e(final Object o) {
                    limited.dispose();
                }
            });
            return limited;
        }

    }

    @Override
    public <B> Signal<B> map(
            final F<? super A, B> function) {

        final MappedSignal<A, B> mapped = new MappedSignal<A, B>(
            this,
            function
        );

        changes().dependentValueSinks.add(mapped);

        return mapped;

    }

    /**
     * If a signal has been "disposed", then it will never execute any effects.
     *
     * @return
     *  True if this signal has been disposed.
     */
    abstract boolean disposed();

    @Override
    public boolean equals(
            final Object object) {

        return object != null && object instanceof Value && equals((Value) object);

    }

    @Override
    public boolean equals(
            final Value value) {

        return value != null && get().equals(value.get());

    }

    @Override
    public int hashCode() {

        return get().hashCode();

    }

}
