package org.jreact.adapt.gwt.focus;

import com.google.gwt.user.client.ui.Focusable;
import fj.Effect;

class SetFocusOnFocusable
        extends Effect<Boolean> {

    private final Focusable focusable;

    public SetFocusOnFocusable(
            final Focusable focusable) {

        this.focusable = focusable;

    }

    @Override
    public void e(
            final Boolean value) {

        focusable.setFocus(value);

    }

}
