package org.jreact.core.impl2;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Tests {@link RelayingPipe#limit(int)}.
 */
public class RelayingPipeLimitedTimesTest {

    private PipeImpl<Character> pipe;
    private RelayingPipeLimitedTimes<Character> relay;

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void createMocks() {

        pipe = (PipeImpl<Character>) mock(PipeImpl.class);

    }

    @BeforeMethod
    public void createTestSubjects() {

        relay = new RelayingPipeLimitedTimes<Character>(pipe, 2);

    }

    @Test
    public void initiallyNotDisposed() {

        assertFalse(relay.disposed());
        verify(pipe, never()).removeValueSink(relay);

    }

    @Test
    public void stillNotDisposed() {

        relay.put('a');

        assertFalse(relay.disposed());
        verify(pipe, never()).removeValueSink(relay);

    }

    @Test
    public void doesGetDisposed() {

        relay.put('a');
        relay.put('a');

        assertTrue(relay.disposed());
        verify(pipe).removeValueSink(relay);

    }

}
