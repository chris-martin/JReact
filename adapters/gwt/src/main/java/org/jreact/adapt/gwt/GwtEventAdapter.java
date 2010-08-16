package org.jreact.adapt.gwt;

import org.jreact.adapt.StreamAdapter;
import org.jreact.core.Signal;

import static org.jreact.adapt.gwt.GwtStreamConnector.gwtStreamConnector;

public class GwtEventAdapter<A>
        extends StreamAdapter<A> {

    public GwtEventAdapter(
            final Signal<Boolean> condition,
            final GwtStreamConnector<? extends A> addHandler) {

        super(
            condition,
            addHandler
        );

    }

    public GwtEventAdapter(
            final Signal<Boolean> condition,
            final GwtHandlerAdder<? extends A> handlerAdder) {

        this(
            condition,
            gwtStreamConnector(
                handlerAdder
            )
        );

    }

}
