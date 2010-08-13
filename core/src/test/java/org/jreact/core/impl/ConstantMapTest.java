package org.jreact.core.impl;

import fj.F;
import org.jreact.core.Signal;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * Tests {@link SimpleConstant#map(F)}.
 */
public class ConstantMapTest {

    private SimpleConstant<String> a;
    private F<String, Integer> length;

    @BeforeMethod
    public void createTestSubjects() {

        a = new SimpleConstant<String>("abc");

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
