package org.jreact.expanded.stream;

import fj.F;
import org.jreact.core.Pipe;
import org.jreact.core.Stream;
import org.jreact.core.impl.Reactives;

import java.util.Arrays;
import java.util.Collection;

public class StreamMerge<A>
        extends F<Collection<Stream<? extends A>>, Stream<A>> {

    @Override
    public Stream<A> f(
            final Collection<Stream<? extends A>> streams) {

        final Pipe<A> pipe = Reactives.pipe();

        for (final Stream<? extends A> stream : streams) {
            stream.loop(
                pipe.putEffect()
            );
        }

        return pipe;

    }

    public static <A> Stream<A> mergeStreams(
            final Stream<? extends A> ... streams) {

        return new StreamMerge<A>().f(
            Arrays.asList(streams)
        );

    }

}
