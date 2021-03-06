package org.jreact.core;

import fj.Effect;
import org.jreact.core.impl.Reactives;
import org.mockito.InOrder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

/**
 * Tests {@link Pipe#put(Object)}.
 */
public class PipePutTest {

    private Pipe<Character> pipe;
    private Effect<Character> effect;

    @BeforeMethod
    public void createTestSubjects() {

        pipe = Reactives.pipe();

    }

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        effect = (Effect<Character>) mock(Effect.class);

    }

    @Test
    public void put() {

        pipe.put('a');

    }

    @Test
    public void loopThenPutA() {

        pipe.loop(effect);
        pipe.put('a');

        verify(effect).e('a');

    }

    @Test
    public void loopThenPutAAA() {

        pipe.loop(effect);
        pipe.put('a');
        pipe.put('a');
        pipe.put('a');

        verify(effect, times(3)).e('a');

    }

    @Test
    public void loopThenPutABAA() {

        final InOrder inOrder = inOrder(effect);

        pipe.loop(effect);
        pipe.put('a');
        pipe.put('b');
        pipe.put('a');
        pipe.put('a');

        inOrder.verify(effect).e('a');
        inOrder.verify(effect).e('b');
        inOrder.verify(effect, times(2)).e('a');

    }

    @Test
    public void putThenLoop() {

        pipe.put('a');
        pipe.loop(effect);
        pipe.put('b');

        verify(effect, times(0)).e('a');
        verify(effect).e('b');

    }

}
