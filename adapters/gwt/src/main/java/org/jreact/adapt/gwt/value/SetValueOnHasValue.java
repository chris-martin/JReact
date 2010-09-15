package org.jreact.adapt.gwt.value;

import com.google.gwt.user.client.ui.HasValue;
import fj.Effect;

class SetValueOnHasValue<A>
        extends Effect<A> {

    private final HasValue<A> hasValue;

    public SetValueOnHasValue(
            final HasValue<A> hasValue) {

        this.hasValue = hasValue;

    }

    @Override
    public void e(
            final A value) {

        hasValue.setValue(
            value
        );

    }

}
