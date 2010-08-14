package org.jreact.core;

import org.jreact.core.impl.Reactives;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests {@link Signal#hashCode()}.
 */
public class SignalHashCodeTest {

    @Test
    public void character() {

        final Signal a = Reactives.signal('a');

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

        final Signal a = Reactives.signal(object);

        assertEquals(a.hashCode(), 5);

    }

}
