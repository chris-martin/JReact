package org.jreact.core.impl;

import fj.F;
import org.jreact.core.Value;

class MappedSignal<A, B>
        extends SignalImpl<B>
        implements ValueSink<A> {

    private final SignalImpl<? extends A> a;
    private final F<? super A, B> function;
    private final PipeImpl<B> changes;

    private B b;

    MappedSignal(
            final SignalImpl<? extends A> a,
            final F<? super A, B> function) {

        this.a = a;
        this.function = function;

        changes = new PipeImpl<B>();

    }

    @Override
    public B get() {

        if (b == null) {
            b = function.f(a.get());
        }

        return b;

    }

    @Override
    public void put(
            final Value<A> value) {

        final B converted = function.f(value.get());
        if (!converted.equals(b)) {
            b = converted;
            changes.put(b);
        };

    }

    @Override
    public StreamImpl<B> changes() {

        return changes;

    }

    @Override
    boolean disposed() {

        return a.disposed();

    }

}
