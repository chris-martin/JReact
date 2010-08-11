package org.jreact.core.impl;

import fj.Effect;
import org.jreact.core.Reactive;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class VariableLimitTest {

    private VariableImpl<Character> var;
    private PipeImpl<Object> dispose;
    private Effect<Character> effect1;
    private Effect<Character> effect2;

    @BeforeMethod
    public void createTestSubjects() {

        var = new VariableImpl<Character>('a');
        dispose = new PipeImpl<Object>();

    }

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        effect1 = (Effect<Character>) Mockito.mock(Effect.class);
        effect2 = (Effect<Character>) Mockito.mock(Effect.class);

    }

    @Test
    public void disposeLoop() {

        final Reactive<Character> limited = var.limit(dispose);

        // dispose
        dispose.put(true);

        // loop
        var.loop(effect1);
        limited.loop(effect2);

        verify(effect1).e('a');
        verify(effect2, never()).e('a');

    }

    @Test
    public void disposeLoopPut() {

        final Reactive<Character> limited = var.limit(dispose);

        // dispose
        dispose.put(true);

        // loop
        var.loop(effect1);
        limited.loop(effect2);

        // put
        var.put('b');

        verify(effect1).e('a');
        verify(effect1).e('b');
        verify(effect2, never()).e('a');
        verify(effect2, never()).e('b');

    }

    @Test
    public void disposePutLoopPut() {

        final Reactive<Character> limited = var.limit(dispose);

        // dispose
        dispose.put(true);

        // put
        var.put('b');

        // loop
        var.loop(effect1);
        limited.loop(effect2);

        // put
        var.put('c');

        verify(effect1, never()).e('a');
        verify(effect1).e('b');
        verify(effect1).e('c');

        verify(effect2, never()).e('a');
        verify(effect2, never()).e('b');
        verify(effect2, never()).e('c');

    }

    @Test
    public void loopDisposePut() {

        final Reactive<Character> limited = var.limit(dispose);

        // loop
        var.loop(effect1);
        limited.loop(effect2);

        // dispose
        dispose.put(true);

        // put
        var.put('b');

        verify(effect1).e('a');
        verify(effect1).e('b');

        verify(effect2).e('a');
        verify(effect2, never()).e('b');

    }

}
