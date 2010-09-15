package org.jreact.adapt.ui.key;

import fj.F;

public class KeyCodePredicate
        extends F<KeyCode, Boolean> {

    private final int code;

    public KeyCodePredicate(
            final int code) {

        this.code = code;

    }

    @Override
    public Boolean f(
            final KeyCode key) {

        return key.code() == code;

    }

}
