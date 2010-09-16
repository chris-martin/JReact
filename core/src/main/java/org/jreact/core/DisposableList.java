package org.jreact.core;

import fj.Effect;
import fj.Unit;

import java.util.HashSet;
import java.util.Set;

import static org.jreact.core.Disposable.disposable;

public final class DisposableList {

    private final Set<Disposable> list;

    private DisposableList(
        final Set<Disposable> list
    ) {
        this.list = list;
    }

    public <A> Disposable<A> add(
        final A a,
        final Effect<Unit> dispose
    ) {
        return add(disposable(a, dispose));
    }

    private <A> Disposable<A> add(
        final Disposable<A> disposable
    ) {
        list.add(disposable);
        return disposable;
    }

    public void remove(
        final Disposable disposable
    ) {
        list.remove(disposable);
    }

    public void dispose() {
        for (final Disposable disposable : list) {
            disposable.dispose();
        }
    }

    public static DisposableList disposableList() {
        return new DisposableList(new HashSet<Disposable>());
    }

}
