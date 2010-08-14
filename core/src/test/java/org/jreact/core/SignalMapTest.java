package org.jreact.core;

import fj.F;
import org.jreact.core.impl.Reactives;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * Tests {@link Signal#map(F)}.
 */
public class SignalMapTest {

    private Signal<String> a;
    private F<String, Integer> length;

    @BeforeMethod
    public void createTestSubjects() {

        a = Reactives.signal("abc");

    }

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        length = mock(F.class);
        when(length.f("abc")).thenReturn(3);

    }

    @Test
    public void get() {

        final Signal<Integer> b = a.map(length);

        assertEquals(b.get(), (Integer) 3);

    }

    @Test
    public void beLazy() {

        final Signal<Integer> b = a.map(length);

        verify(length, never()).f("abc");

    }

    @Test
    public void callOnce() {

        final Signal<Integer> b = a.map(length);
        b.get();

        verify(length).f("abc");

    }

    @Test
    public void callExactlyOnce() {

        final Signal<Integer> b = a.map(length);
        b.get();
        b.get();

        verify(length).f("abc");

    }

}
