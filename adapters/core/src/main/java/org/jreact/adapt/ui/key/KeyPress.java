package org.jreact.adapt.ui.key;

public class KeyPress
        extends Key {

    public KeyPress(
            final char character,
            final KeyModifiers modifiers) {

        super(
            modifiers
        );

        this.character = character;

    }

    private final char character;
    public char character()
    { return character; }

}
