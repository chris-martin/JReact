package org.jreact.adapt.gwt.mouse;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import org.jreact.adapt.gwt.GwtHandlerAdder;
import org.jreact.core.Sink;

public class ClickHandlerAdder
        implements GwtHandlerAdder<ClickEvent> {

    private final HasClickHandlers subject;

    public ClickHandlerAdder(
            final HasClickHandlers subject) {

        this.subject = subject;

    }

    @Override
    public HandlerRegistration addHandler(
            final Sink<? super ClickEvent> sink) {

        return subject.addClickHandler(
            new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    sink.put(event);
                }
            }
        );

    }

}
