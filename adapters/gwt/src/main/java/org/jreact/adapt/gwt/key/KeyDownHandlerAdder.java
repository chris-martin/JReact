package org.jreact.adapt.gwt.key;

import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import org.jreact.adapt.gwt.GwtHandlerAdder;

public class KeyDownHandlerAdder
        implements GwtHandlerAdder<KeyDownEvent> {

    private final HasKeyDownHandlers subject;

    public KeyDownHandlerAdder(
            final HasKeyDownHandlers subject) {

        this.subject = subject;

    }

    @Override
    public HandlerRegistration addHandler(
            final Sink<? super KeyDownEvent> sink) {

        return subject.addKeyDownHandler(
            new KeyDownHandler() {
                @Override
                public void onKeyDown(final KeyDownEvent event) {
                    sink.put(event);
                }
            }
        );

    }

}
