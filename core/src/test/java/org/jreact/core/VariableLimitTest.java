package org.jreact.core;

import fj.Effect;
import org.jreact.core.impl2.Reactives;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Tests {@link Variable#limit(Stream)}.
 */
public class VariableLimitTest {

    private Variable<Character> a;
    private Pipe<Object> dispose1;
    private Pipe<Object> dispose2;
    private Effect<Character> effect1;
    private Effect<Character> effect2;
    private Effect<Character> effect3;

    @BeforeMethod
    public void createTestSubjects() {

        a = Reactives.variable('a');
        dispose1 = Reactives.pipe();
        dispose2 = Reactives.pipe();

    }

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        effect1 = (Effect<Character>) Mockito.mock(Effect.class);
        effect2 = (Effect<Character>) Mockito.mock(Effect.class);
        effect3 = (Effect<Character>) Mockito.mock(Effect.class);

    }

    @Test
    public void disposeLoop() {

        final Reactive<Character> limited = a.limit(dispose1);

        // dispose
        dispose1.put(true);

        // loop
        a.loop(effect1);
        limited.loop(effect2);

        verify(effect1).e('a');
        verify(effect2, never()).e('a');

    }

    @Test
    public void disposeLoopPut() {

        final Reactive<Character> limited = a.limit(dispose1);

        // dispose
        dispose1.put(true);

        // loop
        a.loop(effect1);
        limited.loop(effect2);

        // put
        a.put('b');

        verify(effect1).e('a');
        verify(effect1).e('b');
        verify(effect2, never()).e('a');
        verify(effect2, never()).e('b');

    }

    @Test
    public void disposePutLoopPut() {

        final Reactive<Character> limited = a.limit(dispose1);

        // dispose
        dispose1.put(true);

        // put
        a.put('b');

        // loop
        a.loop(effect1);
        limited.loop(effect2);

        // put
        a.put('c');

        verify(effect1, never()).e('a');
        verify(effect1).e('b');
        verify(effect1).e('c');

        verify(effect2, never()).e('a');
        verify(effect2, never()).e('b');
        verify(effect2, never()).e('c');

    }

    @Test
    public void loopDisposePut() {

        final Reactive<Character> limited = a.limit(dispose1);

        // loop
        a.loop(effect1);
        limited.loop(effect2);

        // dispose
        dispose1.put(true);

        // put
        a.put('b');

        verify(effect1).e('a');
        verify(effect1).e('b');

        verify(effect2).e('a');
        verify(effect2, never()).e('b');

    }

    @Test
    public void limitLimitLoop() {

        final Reactive<Character> b = a.limit(dispose1);
        final Reactive<Character> c = b.limit(dispose1);
        a.loop(effect1);
        b.loop(effect2);
        c.loop(effect3);

        verify(effect1).e('a');
        verify(effect2).e('a');
        verify(effect3).e('a');

    }

    @Test
    public void limitLimitDisposeLoop() {

        final Reactive<Character> b = a.limit(dispose1);
        final Reactive<Character> c = b.limit(dispose1);
        dispose1.put(true);
        a.loop(effect1);
        b.loop(effect2);
        c.loop(effect3);

        verify(effect1).e('a');
        verify(effect2, never()).e('a');
        verify(effect3, never()).e('a');

    }

    @Test
    public void limitLimit2Loop() {

        final Reactive<Character> b = a.limit(dispose1);
        final Reactive<Character> c = b.limit(dispose2);
        a.loop(effect1);
        b.loop(effect2);
        c.loop(effect3);

        verify(effect1).e('a');
        verify(effect2).e('a');
        verify(effect3).e('a');

    }

    @Test
    public void limitLimit2Dispose1Loop() {

        final Reactive<Character> b = a.limit(dispose1);
        final Reactive<Character> c = b.limit(dispose2);
        dispose1.put(true);
        a.loop(effect1);
        b.loop(effect2);
        c.loop(effect3);

        verify(effect1).e('a');
        verify(effect2, never()).e('a');
        verify(effect3, never()).e('a');

    }

    @Test
    public void limitLimit2Dispose2Loop() {

        final Reactive<Character> b = a.limit(dispose1);
        final Reactive<Character> c = b.limit(dispose2);
        dispose2.put(true);
        a.loop(effect1);
        b.loop(effect2);
        c.loop(effect3);

        verify(effect1).e('a');
        verify(effect2).e('a');
        verify(effect3, never()).e('a');

    }

}
