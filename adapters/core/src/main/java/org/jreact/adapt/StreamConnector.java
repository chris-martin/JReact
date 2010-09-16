package org.jreact.adapt;

public interface StreamConnector<A> {

    void attach(
        Sink<? super A> sink
    );

    void detach();

}
