package org.jreact.expanded.wrapper;

import fj.Effect;
import fj.F;
import org.jreact.core.*;
import org.jreact.core.impl2.Reactives;

public class VariableWrapper<A>
        implements Variable<A> {

    private final Variable<A> variable;

    public VariableWrapper(
            final A initialValue) {

        this(
            Reactives.variable(
                initialValue
            )
        );

    }

    public VariableWrapper(
            final Variable<A> variable) {

        this.variable = variable;

    }

    @Override
    public <B> Signal<B> map(
            final F<? super A, ? extends B> function) {

        return variable.map(
            function
        );

    }

    @Override
    public <B, C> Signal<C> compose(
            final Signal<B> signal,
            final F<? super B, ? extends C> function) {

        return variable.compose(
            signal,
            function
        );

    }

    @Override
    public void loop(
            final Effect<? super A> effect) {

        variable.loop(
            effect
        );

    }

    @Override
    public Reactive<A> limit(
            final Stream<?> dispose) {

        return variable.limit(
            dispose
        );

    }

    @Override
    public void put(
            final A value) {

        variable.put(
            value
        );

    }

    @Override
    public A get() {

        return variable.get();

    }

    @Override
    public Stream<? extends A> changes() {

        return variable.changes();

    }

    @Override
    public Effect<A> putEffect() {

        return variable.putEffect();

    }

    @Override
    public <B> Effect<B> putEffect(
            final A value) {

        return variable.putEffect(value);

    }

    @Override
    public boolean equals(
            final Object object) {

        return object != null
            && object instanceof Value
            && equals((Value) object);

    }

    @Override
    public boolean equals(
            final Value value) {

        return value != null
            && get().equals(value.get());

    }

    @Override
    public int hashCode() {

        return get().hashCode();

    }

}
