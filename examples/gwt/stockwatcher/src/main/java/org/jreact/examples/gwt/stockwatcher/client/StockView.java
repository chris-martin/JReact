package org.jreact.examples.gwt.stockwatcher.client;

import org.jreact.adapt.ui.TextEntryWidget;
import org.jreact.core.Stream;

public interface StockView {

    Stream<?> addButtonClick();

    TextEntryWidget newSymbolTextEntry();

    

}
