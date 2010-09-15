package org.jreact.adapt.gwt.key;

import com.google.gwt.event.dom.client.KeyCodeEvent;
import fj.F;
import org.jreact.adapt.ui.key.KeyCode;
import org.jreact.adapt.ui.key.KeyModifiers;

public class GwtKeyCodeEventConverter
        extends F<KeyCodeEvent, KeyCode> {

    @Override
    public KeyCode f(
            final KeyCodeEvent keyCodeEvent) {

        return new KeyCode(
            keyCodeEvent.getNativeKeyCode(),
            new KeyModifiers(
                keyCodeEvent.isAltKeyDown(),
                keyCodeEvent.isControlKeyDown(),
                keyCodeEvent.isMetaKeyDown(),
                keyCodeEvent.isShiftKeyDown()
            )
        );

    }

}
