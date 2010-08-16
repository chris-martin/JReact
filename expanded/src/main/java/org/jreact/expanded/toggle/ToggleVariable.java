package org.jreact.expanded.toggle;

import org.jreact.core.impl.Reactives;
import org.jreact.expanded.wrapper.VariableWrapper;

public class ToggleVariable
        extends VariableWrapper<Boolean> {

    public ToggleVariable(
            final boolean initialValue) {

        super(Reactives.variable(initialValue));

    }

    public void toggle() {

        put(!get());
        
    }

}
