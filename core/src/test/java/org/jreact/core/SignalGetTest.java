package org.jreact.core;

import org.jreact.core.impl.Reactives;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests {@link Signal#get()}.
 */
public class SignalGetTest {

    private Signal<Character> a;

    @BeforeMethod
    public void createTestSubjects() {

        a = Reactives.signal('a');

    }

    @Test
    public void get() {

        assertEquals(a.get(), (Character) 'a');

    }

}
