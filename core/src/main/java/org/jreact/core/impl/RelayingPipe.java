package org.jreact.core.impl;

class RelayingPipe<A>
        extends PipeImpl<A> {

    final StreamImpl<A> originalStream;

    RelayingPipe(
            final StreamImpl<A> originalStream) {

        this.originalStream = originalStream;

    }

    @Override
    void dispose() {

        super.dispose();

        originalStream.removeValueSink(this);

    }

}
