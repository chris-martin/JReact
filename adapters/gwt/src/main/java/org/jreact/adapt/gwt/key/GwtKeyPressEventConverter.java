package org.jreact.adapt.gwt.key;

import com.google.gwt.event.dom.client.KeyPressEvent;
import fj.F;
import org.jreact.adapt.ui.key.KeyModifiers;
import org.jreact.adapt.ui.key.KeyPress;

public class GwtKeyPressEventConverter
        extends F<KeyPressEvent, KeyPress> {

    @Override
    public KeyPress f(
            final KeyPressEvent keyPressEvent) {

        return new KeyPress(
            keyPressEvent.getCharCode(),
            new KeyModifiers(
                keyPressEvent.isAltKeyDown(),
                keyPressEvent.isControlKeyDown(),
                keyPressEvent.isMetaKeyDown(),
                keyPressEvent.isShiftKeyDown()
            )
        );

    }

}
