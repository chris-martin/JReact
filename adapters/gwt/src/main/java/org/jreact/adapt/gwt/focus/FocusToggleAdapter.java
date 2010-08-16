package org.jreact.adapt.gwt.focus;

import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import org.jreact.adapt.gwt.GwtToggleSignal;
import org.jreact.core.Signal;
import org.jreact.expanded.toggle.ToggleSignal;

public class FocusToggleAdapter
        extends ToggleSignal {

    public <T extends HasFocusHandlers & HasBlurHandlers> FocusToggleAdapter(
            final Signal<Boolean> condition,
            final T subject) {

        super(
            new GwtToggleSignal(
                condition,
                new FocusHandlerAdder(subject),
                new BlurHandlerAdder(subject),
                false
            )
        );

    }

}
