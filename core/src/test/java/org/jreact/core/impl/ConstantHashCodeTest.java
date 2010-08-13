package org.jreact.core.impl;

import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;

public class ConstantHashCodeTest {

    @Test
    public void character() {

        final SimpleConstant a = new SimpleConstant<Character>('a');

        assertEquals(a.hashCode(), ((Character) 'a').hashCode());

    }

    @Test
    public void object() {

        final Object object = new Object() {
            @Override
            public int hashCode() {
                return 5;
            }
        };

        final SimpleConstant a = new SimpleConstant<Object>(object);

        assertEquals(a.hashCode(), 5);

    }

}
