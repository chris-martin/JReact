package org.jreact.adapt.gwt.focus;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import org.jreact.adapt.gwt.GwtHandlerAdder;

public class BlurHandlerAdder
        implements GwtHandlerAdder<BlurEvent> {

    private final HasBlurHandlers subject;

    public BlurHandlerAdder(
            final HasBlurHandlers subject) {

        this.subject = subject;

    }

    @Override
    public HandlerRegistration addHandler(
            final Sink<? super BlurEvent> sink) {

        return subject.addBlurHandler(
            new BlurHandler() {
                @Override
                public void onBlur(final BlurEvent event) {
                    sink.put(event);
                }
            }
        );

    }

}
