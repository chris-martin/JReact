package org.jreact.adapt.gwt.key;

import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import org.jreact.adapt.gwt.GwtHandlerAdder;
import org.jreact.core.Sink;

public class KeyPressHandlerAdder
        implements GwtHandlerAdder<KeyPressEvent> {

    private final HasKeyPressHandlers subject;

    public KeyPressHandlerAdder(
            final HasKeyPressHandlers subject) {

        this.subject = subject;

    }

    @Override
    public HandlerRegistration addHandler(
            final Sink<? super KeyPressEvent> sink) {

        return subject.addKeyPressHandler(
            new KeyPressHandler() {
                @Override
                public void onKeyPress(final KeyPressEvent event) {
                    sink.put(event);
                }
            }
        );

    }

}
