package org.jreact.core.impl;

import org.jreact.core.*;

public final class Reactives {

    private Reactives() {

    }

    public static <A> Variable<A> variable(
            final A value) {

        return new VariableImpl<A>(
            value
        );

    }

    public static <A> Signal<A> signal(
            final A value) {

        return new SimpleConstant<A>(
            value
        );

    }

    public static <A> Pipe<A> pipe() {

        return new PipeImpl<A>();

    }

    public static <A> Stream<A> vacuousStream() {

        return VacuousStream.instance();

    }

}
