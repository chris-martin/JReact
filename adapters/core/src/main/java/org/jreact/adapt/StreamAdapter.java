package org.jreact.adapt;

import fj.Effect;
import org.jreact.core.Pipe;
import org.jreact.core.Signal;
import org.jreact.core.Stream;
import org.jreact.core.Variable;
import org.jreact.core.impl2.Reactives;
import org.jreact.expanded.wrapper.StreamWrapper;

public class StreamAdapter<A>
        extends StreamWrapper<A> {

    private static interface V<T> { T get(); }

    public StreamAdapter(
            final Signal<Boolean> condition,
            final StreamConnector<? extends A> connector) {

        super(
            new V<Stream<A>>() {
                @Override
                public Stream<A> get() {

                    final Pipe<A> pipe = Reactives.pipe();

                    final Variable<Boolean> attached = Reactives.variable(false);

                    condition.loop(
                        new IgnoreFirstFalse(
                            new Effect<Boolean>() {
                                @Override
                                public void e(final Boolean value) {
                                    if (value) {
                                        connector.attach(pipe);
                                    } else {
                                        connector.detach();
                                    }
                                }
                            }
                        )
                    );

                    return pipe;

                }
            }.get()
        );

    }

    /**
     * The first time this Effect is executed, if the parameter is false, the call
     * has no effect.  All subsequent calls pass through to the wrapped Effect.
     */
    private static class IgnoreFirstFalse
            extends Effect<Boolean> {

        private final Effect<Boolean> effect;

        private boolean hasExecuted;

        public IgnoreFirstFalse(
                final Effect<Boolean> effect) {

            this.effect = effect;

        }

        @Override
        public void e(
                final Boolean value) {

            if (!hasExecuted) {
                hasExecuted = true;
                if (!value) {
                    return;
                }
            }

            effect.e(value);

        }

    }

}
