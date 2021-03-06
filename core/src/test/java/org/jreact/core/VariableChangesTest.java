package org.jreact.core;

import fj.Effect;
import org.jreact.core.impl.Reactives;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

/**
 * Tests {@link Variable#changes()}.
 */
public class VariableChangesTest {

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

        var.changes().loop(effect);

        verify(effect, never()).e('a');

    }

    @Test
    public void setThenLoop1() {

        var.put('b');

        var.changes().loop(effect);

        verify(effect, never()).e('a');
        verify(effect, never()).e('b');

    }

    @Test
    public void setThenLoop2() {

        final Reactive<? extends Character> changes = var.changes();

        var.put('b');

        changes.loop(effect);

        verify(effect, never()).e('a');
        verify(effect, never()).e('b');

    }

    @Test
    public void loopThenSet() {

        var.changes().loop(effect);

        var.put('b');

        verify(effect, never()).e('a');
        verify(effect).e('b');

    }

    @Test
    public void loopThenSetSame() {

        var.changes().loop(effect);

        var.put('a');

        verify(effect, never()).e('a');

    }

}
