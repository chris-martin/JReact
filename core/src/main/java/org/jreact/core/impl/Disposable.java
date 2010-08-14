package org.jreact.core.impl;

public interface Disposable {

    /**
     * If a signal has been "disposed", then it will never execute any effects.
     *
     * @return
     *  True if this signal has been disposed.
     */
    boolean disposed();

}
