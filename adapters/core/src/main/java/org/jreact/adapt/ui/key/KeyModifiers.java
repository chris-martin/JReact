package org.jreact.adapt.ui.key;

public class KeyModifiers {

    public KeyModifiers(
            final boolean alt,
            final boolean ctrl,
            final boolean meta,
            final boolean shift) {

        this.alt = alt;
        this.ctrl = ctrl;
        this.meta = meta;
        this.shift = shift;

    }

    private final boolean alt;
    public boolean alt()
    { return alt; }

    private final boolean ctrl;
    public boolean ctrl()
    { return ctrl; }

    private final boolean meta;
    public boolean meta()
    { return meta; }

    private final boolean shift;
    public boolean shift()
    { return shift; }

}
