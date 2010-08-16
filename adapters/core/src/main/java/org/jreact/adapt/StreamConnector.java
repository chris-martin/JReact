package org.jreact.adapt;

import org.jreact.core.Sink;

public interface StreamConnector<A> {

    void attach(
        Sink<? super A> sink
    );

    void detach();

}
