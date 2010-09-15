package org.jreact.adapt.gwt.value;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.ui.HasValue;
import fj.Function;
import org.jreact.adapt.gwt.GwtEventAdapter;
import org.jreact.core.Signal;
import org.jreact.expanded.wrapper.VariableWrapper;

public class ValueVariableAdapter<A>
        extends VariableWrapper<A> {

    public <B extends HasValueChangeHandlers<A> & HasValue<A>>
            ValueVariableAdapter(
            final Signal<Boolean> condition,
            final B subject) {

        super(
            subject.getValue()
        );

        condition.changes().filter(
            Function.<Boolean>identity()
        ).loop(
            new CopyHasValueToSink<A>(this, subject)
        );

        new GwtEventAdapter<ValueChangeEvent<A>>(
            condition,
            new ValueChangeHandlerAdder<A>(subject)
        ).map(
            new GetValueFromValueChangeEvent<A>()
        ).loop(
            putEffect()
        );

        loop(
            new SetValueOnHasValue<A>(subject)
        );

    }

}
