package org.jreact.expanded.list;

import fj.Equal;
import fj.data.List;
import fj.data.Option;
import org.jreact.core.Pipe;
import org.jreact.core.Reactive;
import org.jreact.core.Stream;
import org.jreact.expanded.suspend.SuspendableSink;
import org.jreact.expanded.wrapper.VariableWrapper;

import static org.jreact.core.impl2.Reactives.pipe;
import static org.jreact.expanded.suspend.SuspendableSink.suspendableSink;

public class ListVariable<A>
        extends VariableWrapper<List<A>> {

    private final Stream<ListChange> listChangeStream;

    private final SuspendableSink<ListChange> listChangeSink;

    public ListVariable(
            final List<A> list) {

        super(
            list
        );

        final Pipe<ListChange> listChangePipe = pipe();
        listChangeStream = listChangePipe;
        listChangeSink = suspendableSink(
            listChangePipe
        );

    }

    public Reactive<ListChange> listChanges() {

        return

    }

    public ListVariable() {

        this(
            List.<A>nil()
        );

    }

    public void cons(
            final A a) {

        put(
            get().cons(a)
        );

    }

    public void append(
            final List<A> as) {

        put(
            get().append(as)
        );

    }

    public void snoc(
            final A a) {

        append(
            get().snoc(a)
        );

    }

    public Option<Integer> elementIndex(
            final Equal<A> e,
            final A a) {

        return get().elementIndex(e, a);

    }

    public void delete(
            final A a,
            final Equal<A> e) {

        put(
            get().delete(a, e)
        );

    }

}
