package org.jreact.adapt.gwt.focus;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import org.jreact.adapt.gwt.GwtHandlerAdder;
import org.jreact.core.Sink;

public class FocusHandlerAdder
        implements GwtHandlerAdder<FocusEvent> {

    private final HasFocusHandlers subject;

    public FocusHandlerAdder(
            final HasFocusHandlers subject) {

        this.subject = subject;

    }

    @Override
    public HandlerRegistration addHandler(
            final Sink<? super FocusEvent> sink) {

        return subject.addFocusHandler(
            new FocusHandler() {
                @Override
                public void onFocus(final FocusEvent event) {
                    sink.put(event);
                }
            }
        );

    }

}
