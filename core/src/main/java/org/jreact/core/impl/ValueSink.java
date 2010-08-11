package org.jreact.core.impl;

import org.jreact.core.Value;

interface ValueSink<A> {

    void put(
        Value<A> value
    );

}
