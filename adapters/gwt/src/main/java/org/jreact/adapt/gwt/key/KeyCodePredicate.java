package org.jreact.adapt.gwt.key;

import com.google.gwt.event.dom.client.KeyCodeEvent;
import fj.F;

public class KeyCodePredicate
        extends F<KeyCodeEvent, Boolean> {

    private final int keyCode;

    public KeyCodePredicate(
            final int keyCode) {

        this.keyCode = keyCode;

    }

    @Override
    public Boolean f(
            final KeyCodeEvent event) {

        return event.getNativeKeyCode() == keyCode;

    }

}
