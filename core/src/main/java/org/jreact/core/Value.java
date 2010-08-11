package org.jreact.core;

public interface Value<A> {

    A get();

    /**
     * <code>x.equals(object)</code> is equivalent to
     * <code>(object instanceof Value) && x.equals((Value) object)</code>.
     */
    @Override
    boolean equals(
        Object object
    );

    /**
     * Determine's whether this Value's data is equal to another Value's data.
     * <code>x.equals(value)</code> is equivalent to <code>x.get().equals(value.get())</code>.
     */
    boolean equals(
        Value value
    );

    /**
     * Returns the hash code of this Value's data.
     * <code>x.hashCode()</code> is equivalent to <code>x.get().hashCode()</code>.
     */
    @Override
    public int hashCode();

}
