package org.jreact.core.impl;

import fj.Effect;
import fj.F;
import org.jreact.core.Signal;
import org.jreact.core.Stream;

class SimpleConstant<A>
        extends ConstantImpl<A> {

    A value;

    SimpleConstant(
            final A value) {

        this.value = value;

    }

    @Override
    public A get() {

        return value;

    }

}
