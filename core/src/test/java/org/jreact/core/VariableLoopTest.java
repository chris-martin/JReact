package org.jreact.core;

import fj.Effect;
import org.jreact.core.impl.Reactives;
import org.mockito.InOrder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

/**
 * Tests {@link Variable#loop(Effect)}.
 */
public class VariableLoopTest {

    private Variable<Character> var;

    private Effect<Character> effect;

    @BeforeMethod
    public void createTestSubjects() {

        var = Reactives.variable('a');

    }

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        effect = (Effect<Character>) mock(Effect.class);

    }

    @Test
    public void loop() {

        var.loop(effect);

        verify(effect).e('a');

    }

    @Test
    public void setThenLoop() {

        var.put('b');

        var.loop(effect);

        verify(effect).e('b');

    }

    @Test
    public void loopThenSet() {

        final InOrder inOrder = inOrder(effect);

        var.loop(effect);

        var.put('b');

        inOrder.verify(effect).e('a');
        inOrder.verify(effect).e('b');

    }

    @Test
    public void loopThenSetSame() {

        var.loop(effect);

        var.put('a');

        verify(effect).e('a');

    }

}
