package org.jreact.adapt.ui.key;

public class Key {

    public Key(
            final KeyModifiers modifiers) {

        this.modifiers = modifiers;

    }

    private final KeyModifiers modifiers;
    public KeyModifiers modifiers()
    { return modifiers; }

}
