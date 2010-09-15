package org.jreact.expanded.toggle;

import fj.Effect;
import org.jreact.expanded.wrapper.VariableWrapper;

public class ToggleVariable
        extends VariableWrapper<Boolean> {

    public ToggleVariable(
            final boolean initialValue) {

        super(
            initialValue
        );

    }

    public void toggle() {

        put(
            !get()
        );

    }

    public Effect<Object> toggleEffect() {

        return new Effect<Object>() {
            @Override
            public void e(final Object o) {
                toggle();
            }
        };

    }

}
