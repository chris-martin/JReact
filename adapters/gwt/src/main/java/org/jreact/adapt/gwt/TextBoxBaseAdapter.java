package org.jreact.adapt.gwt;

import com.google.gwt.user.client.ui.TextBoxBase;
import org.jreact.adapt.gwt.focus.FocusVariableAdapter;
import org.jreact.adapt.gwt.key.*;
import org.jreact.adapt.gwt.value.ValueVariableAdapter;
import org.jreact.adapt.ui.BaseTextEntryWidget;
import org.jreact.adapt.ui.key.KeyCode;
import org.jreact.adapt.ui.key.KeyPress;
import org.jreact.core.Signal;
import org.jreact.core.Stream;
import org.jreact.core.Variable;

public class TextBoxBaseAdapter
        extends BaseTextEntryWidget {

    private final TextBoxBase widget;

    private TextBoxBaseAdapter(
            final Signal<Boolean> condition,
            final TextBoxBase widget) {

        this.widget = widget;

        value = new ValueVariableAdapter<String>(condition, widget);
        focus = new FocusVariableAdapter(condition, widget);
        keyDown = new KeyDownStreamAdapter(condition, widget)
            .map(new GwtKeyCodeEventConverter());
        keyUp = new KeyUpStreamAdapter(condition, widget)
            .map(new GwtKeyCodeEventConverter());
        keyPress = new KeyPressStreamAdapter(condition, widget)
            .map(new GwtKeyPressEventConverter());

    }

    private final Variable<String> value;
    @Override public Variable<String> value()
    { return value; }

    private final Variable<Boolean> focus;
    @Override public Variable<Boolean> focus()
    { return focus; }

    private final Stream<KeyCode> keyDown;
    @Override public Stream<KeyCode> keyDown()
    { return keyDown; }

    private final Stream<KeyCode> keyUp;
    @Override public Stream<KeyCode> keyUp()
    { return keyUp; }

    private final Stream<KeyPress> keyPress;
    @Override public Stream<KeyPress> keyPress()
    { return keyPress; }

    @Override public void selectAll()
    { widget.selectAll(); }

}
