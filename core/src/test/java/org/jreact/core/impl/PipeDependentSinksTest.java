package org.jreact.core.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

/**
 * Tests {@link PipeImpl#dependentValueSinks}.
 */
public class PipeDependentSinksTest {

    private PipeImpl<Character> pipe;
    private ValueSink<Character> sink;

    @BeforeMethod
    public void createTestSubjects() {

        pipe = new PipeImpl<Character>();

    }

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        sink = mock(ValueSink.class);

    }

    @Test
    public void dependentValueSink1() {

        pipe.dependentValueSinks.add(sink);
        pipe.put('a');

        verify(sink).put(new FinalValue<Character>('a'));

    }

    @Test
    public void dependentValueSink2() {

        pipe.dependentValueSinks.add(sink);
        pipe.put(new FinalValue<Character>('a'));

        verify(sink).put(new FinalValue<Character>('a'));

    }

    @Test
    public void limit0TestDependents() {

        pipe.limit(0);

        assertEquals(pipe.dependentValueSinks.size(), 0);

    }

    @Test
    public void limit1TestDependents() {

        pipe.limit(1);

        assertEquals(pipe.dependentValueSinks.size(), 1);

        pipe.put('a');

        assertEquals(pipe.dependentValueSinks.size(), 0);

    }

    @Test
    public void limit1TestThreeDependents() {

        pipe.limit(1);
        pipe.limit(1);
        pipe.limit(2);

        assertEquals(pipe.dependentValueSinks.size(), 3);

        pipe.put('a');

        assertEquals(pipe.dependentValueSinks.size(), 1);

        pipe.put('a');

        assertEquals(pipe.dependentValueSinks.size(), 0);

    }

}
