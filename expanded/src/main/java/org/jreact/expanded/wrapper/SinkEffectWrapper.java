package org.jreact.expanded.wrapper;

import fj.Effect;
import org.jreact.core.Sink;

public class SinkEffectWrapper<A>
        implements Sink<A> {

    private final Effect<A> effect;

    public SinkEffectWrapper(
            final Effect<A> effect) {

        this.effect = effect;

    }

    @Override
    public void put(
            final A value) {

        effect.e(value);

    }

    @Override
    public Effect<A> putEffect() {

        return new Effect<A>() {

            @Override
            public void e(
                    final A a) {

                SinkEffectWrapper.this.put(a);

            }

        };

    }

    @Override
    public <B> Effect<B> putEffect(
            final A value) {

        return new Effect<B>() {

            @Override
            public void e(
                    final B a) {

                SinkEffectWrapper.this.put(value);

            }

        };

    }

    public static <A> Sink<A> sink(
            final Effect<A> effect) {

        return new SinkEffectWrapper<A>(
            effect
        );

    }

}
