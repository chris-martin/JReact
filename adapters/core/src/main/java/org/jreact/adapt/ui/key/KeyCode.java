package org.jreact.adapt.ui.key;

public class KeyCode
        extends Key {

    public KeyCode(
            final int code,
            final KeyModifiers modifiers) {

        super(
            modifiers
        );

        this.code = code;

    }

    int code;
    public int code()
    { return code; }

}
