package org.jreact.expanded.toggle;

import org.jreact.core.Signal;
import org.jreact.expanded.wrapper.SignalWrapper;

public class ToggleSignal
        extends SignalWrapper<Boolean> {

    public ToggleSignal(
            final Signal<Boolean> signal) {

        super(signal);

    }

}
