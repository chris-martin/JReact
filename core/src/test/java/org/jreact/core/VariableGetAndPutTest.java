package org.jreact.core;

import org.jreact.core.impl.Reactives;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests {@link Variable#get() and {@link Variable#put(Object)}}.
 */
public class VariableGetAndPutTest {

    private Variable<Character> var;

    @BeforeMethod
    public void createTestSubjects() {

        var = Reactives.variable('a');

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
