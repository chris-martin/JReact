package org.jreact.examples.gwt.stockwatcher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.ArrayList;

public class StockWatcher implements EntryPoint {

    public void onModuleLoad() {

        // add the main panel to the HTML element with the id "stockList"
        RootPanel.get("stockList").add(new StockPanel());

    }

}
