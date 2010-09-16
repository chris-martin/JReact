package org.jreact.expanded.wrapper;

import fj.Effect;
import fj.F;
import org.jreact.core.Reactive;
import org.jreact.core.Signal;
import org.jreact.core.Stream;

public class SignalWrapper<A>
        implements Signal<A> {

    private final Signal<A> signal;

    public SignalWrapper(
            final Signal<A> signal) {

        this.signal = signal;

    }

    @Override
    public void loop(
            final Effect<? super A> effect) {

        signal.loop(effect);

    }

    @Override
    public Reactive<A> limit(
            final Stream<?> dispose) {

        return signal.limit(dispose);

    }


    @Override
    public <B> Signal<B> map(
            final F<? super A, ? extends B> function) {

        return signal.map(function);

    }

    @Override
    public <B, C> Signal<C> compose(
            final Signal<B> signal,
            final F<? super B, ? extends C> function) {

        return signal.compose(signal, function);

    }

    @Override
    public A get() {

        return signal.get();

    }

    @Override
    public Stream<? extends A> changes() {

        return signal.changes();

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
