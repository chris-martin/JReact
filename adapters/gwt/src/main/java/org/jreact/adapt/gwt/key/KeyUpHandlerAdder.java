package org.jreact.adapt.gwt.key;

import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import org.jreact.adapt.gwt.GwtHandlerAdder;

public class KeyUpHandlerAdder
        implements GwtHandlerAdder<KeyUpEvent> {

    private final HasKeyUpHandlers subject;

    public KeyUpHandlerAdder(
            final HasKeyUpHandlers subject) {

        this.subject = subject;

    }

    @Override
    public HandlerRegistration addHandler(
            final Sink<? super KeyUpEvent> sink) {

        return subject.addKeyUpHandler(
            new KeyUpHandler() {
                @Override
                public void onKeyUp(final KeyUpEvent event) {
                    sink.put(event);
                }
            }
        );

    }

}
