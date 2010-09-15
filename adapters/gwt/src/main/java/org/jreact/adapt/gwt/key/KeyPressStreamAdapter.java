package org.jreact.adapt.gwt.key;

import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import org.jreact.adapt.gwt.GwtEventAdapter;
import org.jreact.core.Signal;

public class KeyPressStreamAdapter
        extends GwtEventAdapter<KeyPressEvent> {

    public KeyPressStreamAdapter(
            final Signal<Boolean> condition,
            final HasKeyPressHandlers subject) {

        super(
            condition,
            new KeyPressHandlerAdder(subject)
        );

    }

}
