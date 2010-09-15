package org.jreact.adapt.gwt.value;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import org.jreact.adapt.gwt.GwtHandlerAdder;
import org.jreact.core.Sink;

public class ValueChangeHandlerAdder<A>
        implements GwtHandlerAdder<ValueChangeEvent<A>> {

    private final HasValueChangeHandlers<A> subject;

    public ValueChangeHandlerAdder(
            final HasValueChangeHandlers<A> subject) {

        this.subject = subject;

    }

    @Override
    public HandlerRegistration addHandler(
            final Sink<? super ValueChangeEvent<A>> sink) {

        return subject.addValueChangeHandler(
            new ValueChangeHandler<A>() {
                @Override
                public void onValueChange(final ValueChangeEvent<A> event) {
                    sink.put(event);
                }
            }
        );

    }

}
