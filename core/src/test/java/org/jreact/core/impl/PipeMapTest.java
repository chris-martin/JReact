package org.jreact.core.impl;

import fj.Effect;
import fj.F;
import org.jreact.core.Stream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

/**
 * Tests {@link PipeImpl#map(F)}.
 */
public class PipeMapTest {

    private PipeImpl<String> pipe;
    private Effect<String> effect1;
    private Effect<Integer> effect2;
    private F<String, Integer> function;

    @BeforeMethod
    public void createTestSubjects() {

        pipe = new PipeImpl<String>();

    }

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        effect1 = mock(Effect.class);
        effect2 = mock(Effect.class);

        function = mock(F.class);
        when(function.f("abc")).thenReturn(3);
        when(function.f("def")).thenReturn(3);
        when(function.f("xyzzy")).thenReturn(5);

    }

    @Test
    public void putAbc() {

        final Stream<Integer> mapped = pipe.map(function);
        pipe.loop(effect1);
        mapped.loop(effect2);
        pipe.put("abc");

        verify(effect1).e("abc");
        verify(effect2).e(3);

    }

    @Test
    public void putAbcAbc() {

        final Stream<Integer> mapped = pipe.map(function);
        pipe.loop(effect1);
        mapped.loop(effect2);
        pipe.put("abc");
        pipe.put("abc");

        verify(effect1, times(2)).e("abc");
        verify(effect2, times(2)).e(3);

    }

    @Test
    public void putAbcDef() {

        final Stream<Integer> mapped = pipe.map(function);
        pipe.loop(effect1);
        mapped.loop(effect2);
        pipe.put("abc");
        pipe.put("def");

        verify(effect1).e("abc");
        verify(effect1).e("def");
        verify(effect2, times(2)).e(3);

    }

    @Test
    public void putDefXyzzy() {

        final Stream<Integer> mapped = pipe.map(function);
        pipe.loop(effect1);
        mapped.loop(effect2);
        pipe.put("def");
        pipe.put("xyzzy");

        verify(effect1).e("def");
        verify(effect1).e("xyzzy");
        verify(effect2).e(3);
        verify(effect2).e(5);

    }

}
