package org.jreact.adapt.gwt;

import org.jreact.core.Signal;
import org.jreact.expanded.toggle.ToggleSignal;

import static org.jreact.expanded.toggle.Toggles.toggleSignal;

public class GwtToggleSignal
        extends ToggleSignal {

    public GwtToggleSignal(
            final Signal<Boolean> condition,
            final GwtHandlerAdder<?> trueHandlerAdder,
            final GwtHandlerAdder<?> falseHandlerAdder,
            final boolean initialValue) {

        super(
            toggleSignal(
                new GwtEventAdapter<Object>(
                    condition,
                    trueHandlerAdder
                ),
                new GwtEventAdapter<Object>(
                    condition,
                    falseHandlerAdder
                ),
                initialValue
            )
        );

    }

}
