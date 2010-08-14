package org.jreact.core.impl;

import fj.Effect;
import org.jreact.core.Reactive;
import org.jreact.core.Stream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Tests {@link SimpleConstant#limit(Stream)}.
 */
public class ConstantLimitTest {

    private SimpleConstant<Character> a;
    private PipeImpl<Object> dispose1;
    private PipeImpl<Object> dispose2;
    private Effect<Character> effect1;
    private Effect<Character> effect2;
    private Effect<Character> effect3;

    @BeforeMethod
    public void createTestSubjects() {

        a = new SimpleConstant<Character>('a');
        dispose1 = new PipeImpl<Object>();
        dispose2 = new PipeImpl<Object>();

    }

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        effect1 = mock(Effect.class);
        effect2 = mock(Effect.class);
        effect3 = mock(Effect.class);

    }

    @Test
    public void limit() {

        a.limit(dispose1);

    }

    @Test
    public void limitLoop() {

        final Reactive<Character> b = a.limit(dispose1);
        a.loop(effect1);
        b.loop(effect2);

        verify(effect1).e('a');
        verify(effect2).e('a');

    }

    @Test
    public void limitDisposeLoop() {

        final Reactive<Character> b = a.limit(dispose1);
        dispose1.put(true);
        a.loop(effect1);
        b.loop(effect2);

        verify(effect1).e('a');
        verify(effect2, never()).e('a');

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
