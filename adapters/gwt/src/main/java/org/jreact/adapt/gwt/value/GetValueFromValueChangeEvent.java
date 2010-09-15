package org.jreact.adapt.gwt.value;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import fj.F;

class GetValueFromValueChangeEvent<A>
        extends F<ValueChangeEvent<A>, A> {

    @Override
    public A f(
            final ValueChangeEvent<A> event) {

        return event.getValue();

    }

}
