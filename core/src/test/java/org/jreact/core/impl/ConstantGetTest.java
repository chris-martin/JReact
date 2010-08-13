package org.jreact.core.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Tests {@link SimpleConstant#get()}.
 */
public class ConstantGetTest {

    private ConstantImpl<Character> constant;

    @BeforeMethod
    public void createTestSubjects() {

        constant = new SimpleConstant<Character>('a');

    }

    @Test
    public void get() {

        assertEquals(constant.get(), (Character) 'a');

    }

}
