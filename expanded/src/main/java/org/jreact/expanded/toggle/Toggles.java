package org.jreact.expanded.toggle;

import org.jreact.core.Signal;
import org.jreact.core.Stream;
import org.jreact.core.impl2.Reactives;

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
            var.putEffect(true)
        );

        falseStream.loop(
            var.putEffect(false)
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
            var.toggleEffect()
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
