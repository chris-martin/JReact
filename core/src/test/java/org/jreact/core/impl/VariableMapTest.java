package org.jreact.core.impl;

import fj.Effect;
import fj.F;
import org.jreact.core.Signal;
import org.jreact.core.Stream;
import org.jreact.core.Variable;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests {@link VariableImpl#map(F)}.
 */
public class VariableMapTest {

    private VariableImpl<String> var;
    private Effect<String> effect1;
    private Effect<Integer> effect2;
    private F<String, Integer> function;

    @BeforeMethod
    public void createTestSubjects() {

        var = new VariableImpl<String>("abc");

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
    public void putNothing() {

        final Signal<Integer> mapped = var.map(function);
        var.loop(effect1);
        mapped.loop(effect2);

        verify(effect1).e("abc");
        verify(effect2).e(3);

    }

    @Test
    public void putAbc() {

        final Signal<Integer> mapped = var.map(function);
        var.loop(effect1);
        mapped.loop(effect2);

        var.put("abc");

        verify(effect1).e("abc");
        verify(effect2).e(3);

    }

    @Test
    public void putDef() {

        final Signal<Integer> mapped = var.map(function);
        var.loop(effect1);
        mapped.loop(effect2);

        var.put("def");

        verify(effect1).e("abc");
        verify(effect1).e("def");
        verify(effect2).e(3);

    }



    @Test
    public void putDefXyzzy() {

        final Signal<Integer> mapped = var.map(function);
        var.loop(effect1);
        mapped.loop(effect2);

        var.put("def");
        var.put("xyzzy");

        verify(effect1).e("abc");
        verify(effect1).e("def");
        verify(effect1).e("xyzzy");
        verify(effect2).e(3);
        verify(effect2).e(5);

    }

}
