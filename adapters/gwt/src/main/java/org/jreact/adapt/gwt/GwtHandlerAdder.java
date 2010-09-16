package org.jreact.adapt.gwt;

import com.google.gwt.event.shared.HandlerRegistration;

public interface GwtHandlerAdder<A> {

    HandlerRegistration addHandler(
            Sink<? super A> sink);

}
