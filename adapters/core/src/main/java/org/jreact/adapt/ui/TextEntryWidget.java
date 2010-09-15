package org.jreact.adapt.ui;

import fj.Effect;
import org.jreact.adapt.ui.key.KeyCode;
import org.jreact.adapt.ui.key.KeyPress;
import org.jreact.core.Stream;
import org.jreact.core.Variable;

public interface TextEntryWidget {

    Variable<String> value();

    Variable<Boolean> focus();

    Stream<KeyCode> keyDown();
    Stream<KeyCode> keyUp();
    Stream<KeyPress> keyPress();

    void selectAll();
    Effect<Object> selectAllEffect();

}
