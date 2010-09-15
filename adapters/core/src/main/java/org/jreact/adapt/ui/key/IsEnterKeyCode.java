package org.jreact.adapt.ui.key;

import java.awt.event.KeyEvent;

public class IsEnterKeyCode
        extends KeyCodePredicate {

    public IsEnterKeyCode() {

        super(
            KeyEvent.VK_ENTER
        );

    }

}
