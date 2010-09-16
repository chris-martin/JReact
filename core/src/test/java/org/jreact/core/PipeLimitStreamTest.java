package org.jreact.core;

import fj.Effect;
import org.jreact.core.impl2.Reactives;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

/**
 * Tests {@link Pipe#limit(Stream)}.
 */
public class PipeLimitStreamTest {

    private Pipe<Character> pipe;
    private Pipe<Object> dispose;
    private Effect<Character> effect1;
    private Effect<Character> effect2;

    @BeforeMethod
    public void createTestSubjects() {

        pipe = Reactives.pipe();
        dispose = Reactives.pipe();

    }

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        effect1 = (Effect<Character>) Mockito.mock(Effect.class);
        effect2 = (Effect<Character>) Mockito.mock(Effect.class);

    }

    @Test
    public void disposePut() {

        final Reactive<Character> limited = pipe.limit(dispose);
        pipe.loop(effect1);
        limited.loop(effect2);
        dispose.put(true);
        pipe.put('a');

        verify(effect1).e('a');
        verify(effect2, never()).e('a');

    }

    @Test
    public void putDisposePut() {

        final Reactive<Character> limited = pipe.limit(dispose);
        pipe.loop(effect1);
        limited.loop(effect2);

        pipe.put('a');
        dispose.put(true);
        pipe.put('a');

        verify(effect1, times(2)).e('a');
        verify(effect2).e('a');

    }

}
