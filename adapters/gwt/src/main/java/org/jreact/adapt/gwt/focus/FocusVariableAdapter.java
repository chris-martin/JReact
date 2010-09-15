package org.jreact.adapt.gwt.focus;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.user.client.ui.Focusable;
import fj.Function;
import org.jreact.adapt.gwt.GwtEventAdapter;
import org.jreact.core.Signal;
import org.jreact.expanded.wrapper.VariableWrapper;

public class FocusVariableAdapter
        extends VariableWrapper<Boolean> {

    public <T extends HasFocusHandlers & HasBlurHandlers & Focusable>
            FocusVariableAdapter(
            final Signal<Boolean> condition,
            final T subject) {

        super(
            false
        );

        condition.changes().filter(
            Function.<Boolean>identity()
        ).loop(
            putEffect(false)
        );

        new GwtEventAdapter<FocusEvent>(
            condition,
            new FocusHandlerAdder(subject)
        ).loop(
            putEffect(true)
        );

        new GwtEventAdapter<BlurEvent>(
            condition,
            new BlurHandlerAdder(subject)
        ).loop(
            putEffect(false)
        );

        loop(
            new SetFocusOnFocusable(subject)
        );

    }

}
