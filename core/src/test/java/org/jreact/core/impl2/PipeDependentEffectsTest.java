package org.jreact.core.impl2;

import fj.Effect;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests {@link PipeImpl#dependentEffects}.
 */
public class PipeDependentEffectsTest {

    private PipeImpl<Character> pipe;
    private Effect<Character> effect;

    @BeforeMethod
    public void createTestSubjects() {

        pipe = new PipeImpl<Character>();

    }

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        effect = (Effect<Character>) mock(Effect.class);

    }

    @Test
    public void dependentEffect1() {

        pipe.dependentEffects.add(effect);
        pipe.put('a');

        verify(effect).e('a');

    }

    @Test
    public void dependentEffect2() {

        pipe.dependentEffects.add(effect);
        pipe.put(new FinalValue<Character>('a'));

        verify(effect).e('a');

    }

}
