package org.jreact.core;

public interface Varying<A>

{

    Stream<? extends A> changes();

}
