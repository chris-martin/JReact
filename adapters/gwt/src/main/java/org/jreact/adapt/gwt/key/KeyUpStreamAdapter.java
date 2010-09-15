package org.jreact.adapt.gwt.key;

import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyCodeEvent;
import org.jreact.adapt.gwt.GwtEventAdapter;
import org.jreact.core.Signal;

public class KeyUpStreamAdapter
        extends GwtEventAdapter<KeyCodeEvent> {

    public KeyUpStreamAdapter(
            final Signal<Boolean> condition,
            final HasKeyUpHandlers subject) {

        super(
            condition,
            new KeyUpHandlerAdder(subject)
        );

    }

}
