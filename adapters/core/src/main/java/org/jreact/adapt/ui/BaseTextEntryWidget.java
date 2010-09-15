package org.jreact.adapt.ui;

import fj.Effect;

public abstract class BaseTextEntryWidget
        implements TextEntryWidget {

    @Override
    public Effect<Object> selectAllEffect() {

        return new Effect<Object>() {
            @Override
            public void e(final Object o) {
                selectAll();
            }
        };

    }

}
