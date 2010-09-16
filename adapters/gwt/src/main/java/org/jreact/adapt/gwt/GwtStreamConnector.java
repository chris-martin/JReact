package org.jreact.adapt.gwt;

import com.google.gwt.event.shared.HandlerRegistration;
import org.jreact.adapt.StreamConnector;

public class GwtStreamConnector<A>
        implements StreamConnector<A> {

    private final GwtHandlerAdder<A> handlerAdder;

    private HandlerRegistration registration;

    public GwtStreamConnector(
            final GwtHandlerAdder<A> handlerAdder) {

        this.handlerAdder = handlerAdder;

    }

    @Override
    public final void attach(
            final Sink<? super A> sink) {

        registration = handlerAdder.addHandler(sink);

    }

    @Override
    public final void detach() {

        registration.removeHandler();

    }

    public static <A> GwtStreamConnector<A> gwtStreamConnector(
            final GwtHandlerAdder<A> handlerAdder) {

        return new GwtStreamConnector<A>(handlerAdder);

    }

}
