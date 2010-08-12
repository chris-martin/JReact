package org.jreact.core.impl;

import org.jreact.core.Variable;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests {@link VariableImpl#get() and {@link VariableImpl#put(Object)}}.
 */
public class VariableGetAndPutTest {

    private Variable<Character> var;

    @BeforeMethod
    public void createTestSubjects() {

        var = new VariableImpl<Character>('a');

    }

    @Test
    public void get() {

        assertEquals(var.get(), (Character) 'a');

    }

    @Test
    public void setSameValue() {

        var.put('a');

    }

    @Test
    public void setDifferentValue() {

        var.put('b');

    }

    @Test
    public void setMultiple() {

        var.put('a');
        var.put('b');
        var.put('c');
        var.put('c');
        var.put('b');
        var.put('e');

    }

    @Test
    public void setThenGet() {

        var.put('b');

        assertEquals(var.get(), (Character) 'b');

    }

}
