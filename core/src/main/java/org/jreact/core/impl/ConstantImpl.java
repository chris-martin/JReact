package org.jreact.core.impl;

import fj.Effect;
import fj.F;
import org.jreact.core.Reactive;
import org.jreact.core.Signal;
import org.jreact.core.Stream;

abstract class ConstantImpl<A>
        extends AbstractValue<A>
        implements Signal<A> {

    @Override
    public Stream<? extends A> changes() {

        return VacuousStream.instance();

    }

    @Override
    public void loop(
            final Effect<? super A> effect) {

        effect.e(get());

    }

    @Override
    public <B> Signal<B> map(
            final F<A, B> function) {

        return new MappedConstant<A, B>(this, function);

    }

    @Override
    public Reactive<A> limit(
            final Stream<?> dispose) {

        return null;

    }

}
