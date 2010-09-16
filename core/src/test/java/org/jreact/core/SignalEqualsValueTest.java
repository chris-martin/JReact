package org.jreact.core;

import org.jreact.core.impl2.Reactives;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Tests {@link Signal#equals(Value)}.
 */
public class SignalEqualsValueTest {

    private Signal<Character> a;

    @BeforeMethod
    public void createTestSubjects() {

        a = Reactives.signal('a');

    }

    @Test
    public void equalsNull() {

        assertFalse(a.equals((Value) null));

    }

    @Test
    public void equalsSelf() {

        assertTrue(a.equals((Value) a));

    }

    @Test
    public void equalsConstantA() {

        assertTrue(a.equals((Value) Reactives.signal('a')));

    }

    @Test
    @SuppressWarnings("unchecked")
    public void equalsMockValueA() {

        final Value<Character> a2 = mock(Value.class);
        when(a2.get()).thenReturn('a');

        assertTrue(a.equals((Value) a2));
        verify(a2).get();

    }

    @Test
    @SuppressWarnings("unchecked")
    public void equalsMockValueB() {

        final Value<Character> b = mock(Value.class);
        when(b.get()).thenReturn('b');

        assertFalse(a.equals((Value) b));
        verify(b).get();

    }

}
