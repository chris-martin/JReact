package org.jreact.core.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

}
