package org.jreact.core.impl;

import org.jreact.core.Value;

class RelayingPipeLimitedTimes<A>
        extends RelayingPipe<A> {

    int remaining;

    public RelayingPipeLimitedTimes(
            final StreamImpl<A> originalStream,
            final int times) {

        super(originalStream);

        assert times > 0;

        remaining = times;

    }

    @Override
    public void put(
            final A value) {

        super.put(value);

        onPut();

    }

    @Override
    public void put(
            final Value<A> value) {

        super.put(value);

        onPut();

    }

    private void onPut() {

        assert remaining > 0;

        remaining--;

        if (remaining == 0) {
            dispose();
        }

    }

}
