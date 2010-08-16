package org.jreact.adapt.gwt;

import com.google.gwt.event.shared.HandlerRegistration;
import org.jreact.core.Sink;

public interface GwtHandlerAdder<A> {

    HandlerRegistration addHandler(
            Sink<? super A> sink);

}
