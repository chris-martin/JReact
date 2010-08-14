package org.jreact.core;

import org.jreact.core.impl.Reactives;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests {@link Signal#get()}.
 */
public class ConstantGetTest {

    private Signal<Character> constant;

    @BeforeMethod
    public void createTestSubjects() {

        constant = Reactives.constant('a');

    }

    @Test
    public void get() {

        assertEquals(constant.get(), (Character) 'a');

    }

}
