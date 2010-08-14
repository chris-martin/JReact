package org.jreact.core.impl;

import fj.Effect;
import fj.F;
import org.jreact.core.*;

import java.util.ArrayList;
import java.util.List;

abstract class SignalImpl<A>
        implements Signal<A>, Varying<A>, Disposable {

    List<SignalImpl> dependents = new ArrayList<SignalImpl>(); // TODO instantiate lazily

    void dispose() {

        if (!disposed()) {
            for (final SignalImpl d : dependents) {
                d.dispose();
            }
            dependents = null;
        }

    }

    @Override
    public boolean disposed() {

        return dependents == null;

    }

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
            dependents.add(limited);
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
            final F<? super A, ? extends B> function) {

        final MappedSignal<A, B> mapped = new MappedSignal<A, B>(
            this,
            function
        );

        dependents.add(mapped);

        changes().dependentValueSinks.add(mapped);

        return mapped;

    }

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
