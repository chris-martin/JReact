package org.jreact.adapt.gwt.key;

import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.KeyCodeEvent;
import org.jreact.adapt.gwt.GwtEventAdapter;
import org.jreact.core.Signal;

public class KeyDownStreamAdapter
        extends GwtEventAdapter<KeyCodeEvent> {

    public KeyDownStreamAdapter(
            final Signal<Boolean> condition,
            final HasKeyDownHandlers subject) {

        super(
            condition,
            new KeyDownHandlerAdder(subject)
        );

    }

}
