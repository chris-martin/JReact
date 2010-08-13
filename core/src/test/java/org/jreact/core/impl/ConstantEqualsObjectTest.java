package org.jreact.core.impl;

import org.jreact.core.Value;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Tests {@link SimpleConstant#equals(Object)}.
 */
public class ConstantEqualsObjectTest {

    private ConstantImpl<Character> a;

    @BeforeMethod
    public void createTestSubjects() {

        a = new SimpleConstant<Character>('a');

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

        assertTrue(a.equals((Object) new SimpleConstant<Character>('a')));

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
