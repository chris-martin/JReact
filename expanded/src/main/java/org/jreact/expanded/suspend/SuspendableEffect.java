package org.jreact.expanded.suspend;

import fj.Effect;

public class SuspendableEffect<A>
        extends Effect<A>
        implements Suspendable {

    private final Effect<A> effect;

    private boolean suspended;

    public SuspendableEffect(
            final Effect<A> effect) {

        this.effect = effect;

    }

    @Override
    public void suspend(
            final boolean suspended) {

        this.suspended = suspended;

    }

    @Override
    public void e(A a) {

        if (!suspended) {
            effect.e(a);
        }

    }

    public static <A> SuspendableEffect<A> suspendable(
            final Effect<A> effect) {

        return new SuspendableEffect<A>(
            effect
        );

    }

}
