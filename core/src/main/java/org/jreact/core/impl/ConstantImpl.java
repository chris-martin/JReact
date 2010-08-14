package org.jreact.core.impl;

import fj.Effect;
import fj.F;
import org.jreact.core.Reactive;
import org.jreact.core.Signal;
import org.jreact.core.Stream;

import java.util.ArrayList;
import java.util.List;

abstract class ConstantImpl<A>
        extends AbstractValue<A>
        implements Signal<A>, Disposable {

    List<ConstantImpl> dependents = new ArrayList<ConstantImpl>(); // TODO instantiate lazily

    void dispose() {

        if (!disposed()) {
            for (final ConstantImpl d : dependents) {
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
    public Stream<? extends A> changes() {

        return VacuousStream.instance();

    }

    @Override
    public void loop(
            final Effect<? super A> effect) {

        if (!disposed()) {
            effect.e(get());
        }

    }

    @Override
    public <B> Signal<B> map(
            final F<? super A, B> function) {

        if (disposed()) {
            throw new IllegalStateException();
        } else {
            final MappedConstant<A, B> mapped = new MappedConstant<A, B>(this, function);
            dependents.add(mapped);
            return mapped;
        }

    }

    @Override
    public Reactive<A> limit(
            final Stream<?> dispose) {

        if (disposed()) {

            return VacuousStream.instance();

        } else {

            final RelayingConstant<A> limited = new RelayingConstant<A>(this);

            dependents.add(limited);

            dispose.limit(1).loop(new Effect<Object>() {
                @Override
                public void e(Object o) {
                    limited.dispose();
                }
            });

            return limited;

        }

    }

}
