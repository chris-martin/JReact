package org.jreact.core;

import org.jreact.core.impl2.Reactives;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Tests {@link org.jreact.core.impl2.SimpleConstant#equals(Object)}.
 */
public class SignalEqualsObjectTest {

    private Signal<Character> a;

    @BeforeMethod
    public void createTestSubjects() {

        a = Reactives.signal('a');

    }

    @Test
    public void equalsNull() {

        assertFalse(a.equals((Object) null));

    }

    @Test
    public void equalsA() {

        assertFalse(a.equals((Object) 'a'));

    }

    @Test
    public void equalsSelf() {

        assertTrue(a.equals((Object) a));

    }

    @Test
    public void equalsConstantA() {

        assertTrue(a.equals((Object) Reactives.signal('a')));

    }

    @Test
    @SuppressWarnings("unchecked")
    public void equalsMockValueA() {

        final Value<Character> a2 = mock(Value.class);
        when(a2.get()).thenReturn('a');

        assertTrue(a.equals((Object) a2));
        verify(a2).get();

    }

    @Test
    @SuppressWarnings("unchecked")
    public void equalsMockValueB() {

        final Value<Character> b = mock(Value.class);
        when(b.get()).thenReturn('b');

        assertFalse(a.equals((Object) b));
        verify(b).get();

    }

}
