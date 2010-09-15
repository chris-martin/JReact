package org.jreact.adapt.gwt.value;

import com.google.gwt.user.client.ui.HasValue;
import fj.Effect;
import org.jreact.core.Sink;

public class CopyHasValueToSink<A>
        extends Effect<Object> {

    private final Sink<A> sink;
    private final HasValue<A> hasValue;

    public CopyHasValueToSink(
            final Sink<A> sink,
            final HasValue<A> hasValue) {

        this.sink = sink;
        this.hasValue = hasValue;

    }

    @Override
    public void e(
            final Object o) {

        sink.put(
            hasValue.getValue()
        );

    }

}
