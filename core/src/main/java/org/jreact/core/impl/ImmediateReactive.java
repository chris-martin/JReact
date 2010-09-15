package org.jreact.core.impl;

import fj.F;
import fj.Unit;
import org.jreact.core.Reactive;
import org.jreact.core.Stream;

class ImmediateReactive<A>
        implements Reactive<A> {

    public ImmediateReactive(
            final F<Unit, ? extends A> initialValue,
            final Stream<? extends A> changes) {
        
    }

}
