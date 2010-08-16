package org.jreact.adapt;

import org.jreact.core.Signal;
import org.jreact.expanded.toggle.ToggleSignal;

import static org.jreact.expanded.toggle.Toggles.toggleSignal;

public class ToggleAdapter<A, B>
        extends ToggleSignal {

    public ToggleAdapter(
            final Signal<Boolean> condition,
            final StreamConnector<A> trueConnector,
            final StreamConnector<B> falseConnector,
            final boolean initialState) {

        super(
            toggleSignal(
                new StreamAdapter<A>(
                    condition,
                    trueConnector
                ),
                new StreamAdapter<B>(
                    condition,
                    falseConnector
                ),
                initialState
            )
        );

    }

}
