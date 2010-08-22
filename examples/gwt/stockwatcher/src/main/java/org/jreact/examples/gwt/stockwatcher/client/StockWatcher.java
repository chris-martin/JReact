package org.jreact.examples.gwt.stockwatcher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.Date;

public class StockWatcher implements EntryPoint {

    private static final int REFRESH_INTERVAL = 5000; // ms

    private StockPriceServiceAsync stockPriceSvc;

    private VerticalPanel mainPanel = new VerticalPanel();
    private FlexTable stocksFlexTable = new FlexTable();
    private HorizontalPanel addPanel = new HorizontalPanel();
    private TextBox newSymbolTextBox = new TextBox();
    private Button addButton = new Button("Add");
    private Label lastUpdatedLabel = new Label();
    private Label errorMsgLabel = new Label();

    private ArrayList<String> stocks = new ArrayList<String>();

    public void onModuleLoad() {

        // set up stock list watch list
        stocksFlexTable.setText(0, 0, "Symbol");
        stocksFlexTable.setText(0, 1, "Price");
        stocksFlexTable.setText(0, 2, "Change");
        stocksFlexTable.setText(0, 3, "Remove");

        // set up event listeners for adding a new stock
        addButton.addClickListener(
            new ClickListener() {
                public void onClick(Widget sender) {
                    addStock();
                }
            }
        );

        newSymbolTextBox.addKeyboardListener(
            new KeyboardListenerAdapter() {
                @Override
                public void onKeyDown(
                        Widget sender,
                        char keyCode,
                        int modifiers) {
                    if (keyCode == KEY_ENTER) {
                        addStock();
                    }
                }
            }
        );

        // assemble Add Stock panel
        addPanel.add(newSymbolTextBox);
        addPanel.add(addButton);
        addPanel.addStyleName("addPanel");

        // assemble main panel
        errorMsgLabel.setStyleName("errorMessage");
        errorMsgLabel.setVisible(false);
        mainPanel.add(errorMsgLabel);
        mainPanel.add(stocksFlexTable);
        mainPanel.add(addPanel);
        mainPanel.add(lastUpdatedLabel);

        // add the main panel to the HTML element with the id "stockList"
        RootPanel.get("stockList").add(mainPanel);

        // setup timer to refresh list automatically
        Timer refreshTimer = new Timer() {
            public void run() {
                refreshWatchList();
            }
        };
        refreshTimer.scheduleRepeating(REFRESH_INTERVAL);

        // move cursor focus to the text box
        newSymbolTextBox.setFocus(true);

    }

    private void addStock() {

        final String symbol = newSymbolTextBox.getText().toUpperCase().trim();
        newSymbolTextBox.setFocus(true);

        // symbol must be between 1 and 10 chars that are numbers, letters, or dots
        if (!symbol.matches("^[0-9a-zA-Z\\.]{1,10}$")) {
            Window.alert("'" + symbol + "' is not a valid symbol.");
            newSymbolTextBox.selectAll();
            return;
        }

        newSymbolTextBox.setText("");

        // don't add the stock if it's already in the watch list
        if (stocks.contains(symbol)) {
            return;
        }

        // add the stock to the list
        int row = stocksFlexTable.getRowCount();
        stocks.add(symbol);
        stocksFlexTable.setText(row, 0, symbol);

        Button removeStock = new Button("x");
        removeStock.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                int removedIndex = stocks.indexOf(symbol);
                stocks.remove(removedIndex);
                stocksFlexTable.removeRow(removedIndex+1);
            }
        });
        stocksFlexTable.setWidget(row, 3, removeStock);

        // get stock price
        refreshWatchList();

    }

    private void refreshWatchList() {

        // lazy initialization of service proxy
        if (stockPriceSvc == null) {
            stockPriceSvc = GWT.create(StockPriceService.class);
        }

        AsyncCallback<StockPrice[]> callback = new AsyncCallback<StockPrice[]>() {

            public void onFailure(Throwable caught) {
                // display the error message above the watch list
                String details = caught.getMessage();
                if (caught instanceof DelistedException) {
                    details = "Company '" + ((DelistedException)caught).getSymbol() + "' was delisted";
                }

                errorMsgLabel.setText("Error: " + details);
                errorMsgLabel.setVisible(true);
            }

            public void onSuccess(
                    StockPrice[] result) {
                updateTable(result);
            }
        };

        // make the call to the stock price service
        stockPriceSvc.getPrices(stocks.toArray(new String[0]), callback);

    }

private void updateTable(StockPrice[] prices) {
  for (int i=0; i<prices.length; i++) {
    updateTable(prices[i]);
  }

  // change the last update timestamp
  lastUpdatedLabel.setText("Last update : " +
      DateTimeFormat.getMediumDateTimeFormat().format(new Date()));

      // clear any errors
  errorMsgLabel.setVisible(false);
    
}

private void updateTable(StockPrice price) {
  // make sure the stock is still in our watch list
  if (!stocks.contains(price.getSymbol())) {
    return;
  }

  int row = stocks.indexOf(price.getSymbol()) + 1;

  // apply nice formatting to price and change
  String priceText = NumberFormat.getFormat("#,##0.00").format(price.getPrice());
  NumberFormat changeFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00");
  String changeText = changeFormat.format(price.getChange());
  String changePercentText = changeFormat.format(price.getChangePercent());

  // update the watch list with the new values
  stocksFlexTable.setText(row, 1, priceText);
  stocksFlexTable.setText(row, 2, changeText + " (" + changePercentText + "%)");
}

}
