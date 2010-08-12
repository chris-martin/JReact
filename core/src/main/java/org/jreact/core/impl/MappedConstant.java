package org.jreact.core.impl;

import fj.F;
import org.jreact.core.Value;

public class MappedConstant<A, B>
        extends ConstantImpl<B> {

    Value<A> a;
    F<? super A, B> function;
    B b;

    MappedConstant(
            final Value<A> a,
            final F<? super A, B> function) {

        this.a = a;
        this.function = function;

    }

    @Override
    public B get() {

        if (b == null) {
            b = function.f(a.get());
            a = null;
            function = null;
        }

        return b;

    }

}
