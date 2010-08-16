package org.jreact.expanded.toggle;

import fj.Effect;
import org.jreact.core.Signal;
import org.jreact.core.Stream;
import org.jreact.core.impl.Reactives;

public final class Toggles {

    private Toggles() { }

    public static Signal<Boolean> toggleSignal(
            final Stream<?> trueStream,
            final Stream<?> falseStream,
            final boolean initialValue) {

        final ToggleVariable var = new ToggleVariable(
            initialValue
        );

        trueStream.loop(
            new Effect<Object>() {
                @Override
                public void e(final Object o) {
                    var.put(true);
                }
            }
        );

        falseStream.loop(
            new Effect<Object>() {
                @Override
                public void e(final Object o) {
                    var.put(false);
                }
            }
        );

        return var;

    }

    public static Signal<Boolean> until(
            final boolean initialValue,
            final Stream<?> stream) {

        final ToggleVariable var = new ToggleVariable(
            initialValue
        );

        stream.limit(1).loop(
            new Effect<Object>() {
                @Override
                public void e(final Object o) {
                    var.toggle();
                }
            }
        );

        return var;

    }

    private static final Signal<Boolean> TRUE = new ToggleSignal(
        Reactives.signal(true)
    );

    private static final Signal<Boolean> FALSE = new ToggleSignal(
        Reactives.signal(false)
    );

    public static Signal<Boolean> trueSignal() {

        return TRUE;

    }

    public static Signal<Boolean> falseSignal() {

        return FALSE;

    }

    public static Signal<Boolean> trueUntil(
            final Stream<?> stream) {

        return until(
            true,
            stream
        );

    }

    public static Signal<Boolean> falseUntil(
            final Stream<?> stream) {

        return until(
            false,
            stream
        );

    }

}
