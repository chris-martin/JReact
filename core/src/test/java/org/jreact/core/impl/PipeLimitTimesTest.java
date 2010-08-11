package org.jreact.core.impl;

import fj.Effect;
import org.jreact.core.Reactive;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class PipeLimitTimesTest {

    private PipeImpl<Character> pipe;
    private Effect<Character> effect1;
    private Effect<Character> effect2;

    @BeforeMethod
    public void createTestSubjects() {

        pipe = new PipeImpl<Character>();

    }

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        effect1 = (Effect<Character>) Mockito.mock(Effect.class);
        effect2 = (Effect<Character>) Mockito.mock(Effect.class);

    }

    @Test
    public void limit0TestDependents() {

        pipe.limit(0);

        assertEquals(pipe.dependentValueSinks.size(), 0);

    }

    @Test
    public void limit0TestLooping() {

        final Reactive<Character> limited = pipe.limit(0);
        pipe.loop(effect1);
        limited.loop(effect2);
        pipe.put('a');

        verify(effect1).e('a');
        verify(effect2, never()).e('a');

    }

    @Test
    public void limit1TestDependents() {

        final Reactive<Character> limited = pipe.limit(1);

        assertEquals(pipe.dependentValueSinks.size(), 1);

        pipe.put('a');

        assertEquals(pipe.dependentValueSinks.size(), 0);

    }

    @Test
    public void limit1TestLooping() {

        final Reactive<Character> limited = pipe.limit(1);
        pipe.loop(effect1);
        limited.loop(effect2);
        pipe.put('a');
        pipe.put('a');

        verify(effect1, times(2)).e('a');
        verify(effect2).e('a');

    }

    @Test
    public void limit2TestLooping() {

        final Reactive<Character> limited = pipe.limit(2);
        pipe.loop(effect1);
        limited.loop(effect2);
        pipe.put('a');
        pipe.put('a');
        pipe.put('a');
        pipe.put('a');
        pipe.put('a');

        verify(effect1, times(5)).e('a');
        verify(effect2, times(2)).e('a');

    }

}
